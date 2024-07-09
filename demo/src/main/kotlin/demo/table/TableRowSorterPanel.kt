package demo.table

import com.github.funczz.kotlin.swing.addFunctionalDocumentListener
import com.github.funczz.kotlin.swing.util.getFontMetricsStringBounds
import demo.AbstractGridBagJPanel
import java.awt.GridBagConstraints
import java.awt.Insets
import java.util.*
import javax.swing.*
import javax.swing.table.DefaultTableModel
import javax.swing.table.TableRowSorter
import kotlin.math.ceil

class TableRowSorterPanel : AbstractGridBagJPanel() {

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

    private val tableRowSorter = TableRowSorter(tableModel)

    private val jTable = JTable().also {
        it.rowSorter = tableRowSorter
        it.model = tableModel
        it.columnModel.also { cm ->
            val c = cm.getColumn(0)
            c.maxWidth = ceil(it.getFontMetricsStringBounds("9999").width).toInt()
        }
    }

    private val jScrollPane = JScrollPane(jTable)

    private val searchLabel = JLabel("search:")

    private val searchField = JTextField().also { tf ->
        tf.document.addFunctionalDocumentListener {
            println("onChangedUpdate")
            tableRowSorter.rowFilter =
                RowFilter.regexFilter(tf.text, 0, 1)
        }
    }

    init {
        addComponent(
            component = searchLabel,
            gridx = 0,
            gridy = 0,
            anchor = GridBagConstraints.WEST,
        )
        addComponent(
            component = searchField,
            gridx = 1,
            gridy = 0,
            fill = GridBagConstraints.HORIZONTAL,
            insets = Insets(10, 0, 10, 0),
            weightx = 1.0,
        )
        addComponent(
            component = jScrollPane,
            gridx = 0,
            gridy = 1,
            gridwidth = 2,
            fill = GridBagConstraints.BOTH,
            weightx = 1.0,
            weighty = 1.0,
        )
    }

}