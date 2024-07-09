package demo.table

import com.github.funczz.kotlin.swing.table.AbstractFutureReadableTableModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AbstractFutureReadableTableModelImpl(

    private val tableData: List<Pair<String, String>>,

    private val executor: ExecutorService = Executors.newCachedThreadPool(),

    ) : AbstractFutureReadableTableModel<Pair<String, String>>() {

    override fun executor(): ExecutorService = executor

    override fun getRowCount(): Int = tableData.size

    override fun getRealValueAt(rowObject: Pair<String, String>, columnIndex: Int): Any {
        return when (columnIndex) {
            0 -> rowObject.first
            1 -> rowObject.second
            else -> throw IndexOutOfBoundsException("columnIndex: $columnIndex")
        }
    }

    override fun getPlaceholderValueAt(columnIndex: Int): Any {
        return when (columnIndex) {
            0,
            1 -> "N/A"

            else -> throw IndexOutOfBoundsException("columnIndex: $columnIndex")
        }
    }

    override fun loadList(offset: Int, length: Int): MutableList<Pair<String, String>> {
        val result = mutableListOf<Pair<String, String>>()
        repeat(length) {
            val rowIndex = offset + it
            if (rowIndex >= tableData.size) throw IndexOutOfBoundsException("rowIndex: $rowIndex")
            val element = tableData[rowIndex]
            result.add(element)
        }
        return result
    }

    override fun getColumnCount(): Int = 2

    companion object {
        private const val serialVersionUID: Long = -7193923078569397732L
    }

}