package demo.table

import com.github.funczz.kotlin.swing.table.AlternateRowBackgroundJTable
import com.github.funczz.kotlin.swing.util.getFontMetricsStringBounds
import demo.AbstractGridBagJPanel
import java.awt.Color
import java.awt.GridBagConstraints
import java.util.*
import javax.swing.JScrollPane
import javax.swing.UIManager
import javax.swing.table.DefaultTableModel
import kotlin.math.ceil

class JTableHeaderPanel : AbstractGridBagJPanel() {

    private val list = listOf(
        "How razorback-jumping frogs can level six piqued gymnasts!",
        "Cozy lummox gives smart squid who asks for job pen.",
        "The quick brown fox jumps over the lazy dog.",
        "Jackdaws love my big sphinx of quartz.",
    )

    private val tableModel = DefaultTableModel(arrayOf("No.", "Pangram"), 0).also {
        val random = Random()
        repeat(10) { i ->
            it.addRow(arrayOf(i.toString(), list[random.nextInt(list.size)]))
        }
    }

    private val jTable = AlternateRowBackgroundJTable(
        rowBackground = UIManager.getColor("Table.background"),
        alternateRowBackground = Color.LIGHT_GRAY,
        dm = tableModel
    ).also {
        it.columnModel.also { cm ->
            val c = cm.getColumn(0)
            c.maxWidth = ceil(it.getFontMetricsStringBounds("9999").width).toInt()
        }
    }

    private val jScrollPane = JScrollPane(jTable)

    init {
        addComponent(
            component = jScrollPane,
            gridx = 0,
            gridy = 0,
            fill = GridBagConstraints.BOTH,
            weightx = 1.0,
            weighty = 1.0,
        )
    }

}