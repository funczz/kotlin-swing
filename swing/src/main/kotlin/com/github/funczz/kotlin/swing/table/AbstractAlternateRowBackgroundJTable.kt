package com.github.funczz.kotlin.swing.table

import java.awt.Color
import java.awt.Component
import javax.swing.JTable
import javax.swing.ListSelectionModel
import javax.swing.table.TableCellRenderer
import javax.swing.table.TableColumnModel
import javax.swing.table.TableModel

@Suppress("Unused")
abstract class AbstractAlternateRowBackgroundJTable(
    dm: TableModel? = null,
    cm: TableColumnModel? = null,
    sm: ListSelectionModel? = null
) : JTable(dm, cm, sm) {

    abstract fun getRowBackground(): Color

    abstract fun getAlternateRowBackground(): Color

    override fun prepareRenderer(renderer: TableCellRenderer?, row: Int, column: Int): Component {
        return super.prepareRenderer(renderer, row, column).also {
            if (it.background != getSelectionBackground()) {
                it.background = if (row % 2 == 0) getRowBackground() else getAlternateRowBackground()
            }
        }
    }

}