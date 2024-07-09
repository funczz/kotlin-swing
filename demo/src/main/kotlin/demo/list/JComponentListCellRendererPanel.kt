package demo.list

import com.github.funczz.kotlin.swing.list.JComponentListCellRenderer
import demo.AbstractGridBagJPanel
import java.awt.Color
import java.awt.GridBagConstraints
import javax.swing.DefaultListModel
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JScrollPane

class JComponentListCellRendererPanel(

    selectionBackground: Color? = null,

    ) : AbstractGridBagJPanel() {

    private val label = JLabel("List")

    private val listModel = DefaultListModel<JComponentListCellRendererElementPanel>().also {
        (0..99).map {
            JComponentListCellRendererElementPanel(it)
        }.forEach { p ->
            it.addElement(p)
        }
    }

    private val listCellRenderer = JComponentListCellRenderer<JComponentListCellRendererElementPanel>(
        selectionBackground = selectionBackground
    )

    private val jList = JList(listModel).also {
        it.setCellRenderer(listCellRenderer)
    }

    private val jScrollPane = JScrollPane(jList).also {
        //スクロール量を調整
        //it.verticalScrollBar.unitIncrement = JPanelListElementPanel(0).preferredSize.height / 4
        it.verticalScrollBar.unitIncrement = 10
    }

    init {
        addComponent(
            component = label,
            gridx = 0,
            gridy = 0,
            anchor = GridBagConstraints.WEST,
        )
        addComponent(
            component = jScrollPane,
            gridx = 0,
            gridy = 1,
            fill = GridBagConstraints.BOTH,
            weightx = 1.0,
            weighty = 1.0,
        )

        jList.addListSelectionListener {
            val result = jList.selectedIndices.joinToString(" ")
            println("selected: $result")
        }
    }

}