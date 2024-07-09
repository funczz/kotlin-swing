package com.github.funczz.kotlin.swing.table

import java.awt.EventQueue
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.FutureTask
import javax.swing.table.AbstractTableModel

/**
 * https://gitlab.com/gitlab-org/security-products/zaproxy/-/blob/8ad9b4daee7ec12312d3d249219fea76278828a8/src/org/zaproxy/zap/utils/PagingTableModel.java
 */
@Suppress("Unused")
abstract class AbstractFutureReadableTableModel<T> : AbstractTableModel() {

    /**
     * 行の読み込みを行う Executor を返す。
     * @return Executor
     */
    abstract fun executor(): Executor

    /**
     * 全行数を返す。
     * @return 全行数
     */
    abstract override fun getRowCount(): Int

    /**
     * 行の読み込み時に呼び出される。
     * @param rowObject
     * @param columnIndex
     * @return カラムの値
     */
    protected abstract fun getRealValueAt(rowObject: T, columnIndex: Int): Any

    /**
     * @param columnIndex
     * @return 読み込み中にカラムに渡す値
     */
    protected abstract fun getPlaceholderValueAt(columnIndex: Int): Any

    /**
     * テーブル更新時に呼び出される。
     * @param offset 表示開始時のオフセット値
     * @param length 表示行数
     * @return 表示対象のみ抽出した List
     * @see List#get(int)
     */
    protected abstract fun loadList(offset: Int, length: Int): MutableList<T>

    private var dataOffset = 0

    private var data: MutableList<T> = mutableListOf()

    private val pending = TreeSet<Segment>()

    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) =
        throw NotImplementedError()

    override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean = false

    override fun fireTableDataChanged() {
        clear()
        super.fireTableDataChanged()
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        val rowObject = getRowObject(rowIndex)
        if (rowObject == null) {
            schedule(rowIndex)
            return getPlaceholderValueAt(columnIndex)
        }

        return getRealValueAt(rowObject, columnIndex)
    }

    private fun getRowObject(rowIndex: Int): T? {
        val pageIndex = rowIndex - dataOffset
        return if (pageIndex >= 0 && pageIndex < data.size) {
            data[pageIndex]
        } else null
    }

    private fun schedule(offset: Int) {
        if (isPending(offset)) {
            return
        }

        val startOffset = 0.coerceAtLeast(offset) //Math.max(0, offset)
        val length = 1

        load(startOffset, length)
    }

    private fun isPending(offset: Int): Boolean {
        val pendingCount = pending.size

        if (pendingCount == 0) {
            return false
        }

        if (pendingCount == 1) {
            val seg = pending.first()
            return seg.contains(offset)
        }

        val low = Segment(offset, 0)
        val high = Segment(offset + 1, 0)

        for (seg in pending.subSet(low, high)) {
            if (seg.contains(offset)) {
                return true
            }
        }

        return false
    }

    private fun load(startOffset: Int, @Suppress("SameParameterValue") length: Int) {
        val futureTask = FutureTask {
            val segment = Segment(startOffset, length)
            val list: MutableList<T> = mutableListOf()
            try {
                EventQueue.invokeLater {
                    pending.add(segment)
                }
                list.addAll(loadList(segment.base, segment.length))
            } catch (e: Exception) {
                return@FutureTask
            } finally {
                EventQueue.invokeLater {
                    setData(segment.base, list)
                    pending.removeIf { it == segment }
                }
            }
        }

        executor().execute(futureTask)
    }

    private fun setData(offset: Int, page: MutableList<T>) {
        val lastRow = offset + page.size - 1
        dataOffset = offset
        data = page
        fireTableRowsUpdated(offset, lastRow)
    }

    private fun clear() {
        data.clear()
        data = mutableListOf()
        pending.clear()
    }

    internal class Segment(val base: Int, val length: Int) : Comparable<Segment> {

        operator fun contains(pos: Int): Boolean {
            return base <= pos && pos < base + length
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other != null && other is Segment) {
                val hasSameBase = base == other.base
                val hasSameLength = length == other.length
                return hasSameBase && hasSameLength
            }
            return false
        }

        override fun hashCode(): Int {
            return Objects.hash(base, length)
        }

        override fun compareTo(other: Segment): Int {
            val d = base - other.base
            return if (d != 0) {
                d
            } else length - other.length
        }
    }

}
