package com.github.funczz.kotlin.swing.table

import java.awt.Color
import javax.swing.ListSelectionModel
import javax.swing.table.TableColumnModel
import javax.swing.table.TableModel

@Suppress("Unused")
open class AlternateRowBackgroundJTable @JvmOverloads constructor(
    private val rowBackground: Color,
    private val alternateRowBackground: Color,
    dm: TableModel? = null,
    cm: TableColumnModel? = null,
    sm: ListSelectionModel? = null
) : AbstractAlternateRowBackgroundJTable(dm, cm, sm) {

    override fun getRowBackground(): Color {
        return rowBackground
    }

    override fun getAlternateRowBackground(): Color {
        return alternateRowBackground
    }

}