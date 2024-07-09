package demo.list

import com.github.funczz.kotlin.swing.list.JPanelList
import com.github.funczz.kotlin.swing.util.getScrollBarSize
import demo.AbstractGridBagJPanel
import java.awt.Dimension
import java.awt.GridBagConstraints
import javax.swing.BoxLayout
import javax.swing.DefaultListModel
import javax.swing.JButton
import javax.swing.JScrollPane

class JPanelListPanel(

    private val listModel: DefaultListModel<JPanelListElementPanel>,

    axis: Int,

    ) : AbstractGridBagJPanel() {

    private val addButton = JButton("add").also {
        val dimension = it.preferredSize
        it.minimumSize = dimension
        it.maximumSize = dimension
        it.addActionListener {
            listModel.addElement(JPanelListElementPanel(listModel.size))
        }
    }

    private val jPanelList = JPanelList<JPanelListElementPanel>(listModel = listModel, axis = axis).also {
        val dimension = it.preferredSize
        it.minimumSize = dimension
        it.maximumSize = dimension
    }

    private val jScrollPane = JScrollPane(jPanelList).also {
        val size = it.getScrollBarSize()
        val dimension = when (axis) {
            BoxLayout.Y_AXIS -> {
                Dimension(
                    jPanelList.preferredSize.width + size.width,
                    500
                )
            }

            BoxLayout.X_AXIS -> {
                Dimension(
                    500,
                    jPanelList.preferredSize.height + size.height
                )
            }

            else -> throw IllegalArgumentException("axis: $axis")
        }
        it.preferredSize = dimension
        it.minimumSize = dimension

        //スクロール量を調整
        //it.verticalScrollBar.unitIncrement = JPanelListElementPanel(0).preferredSize.height / 4
        it.verticalScrollBar.unitIncrement = 10
    }

    init {
        addComponent(
            component = addButton,
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
    }

    companion object {
        private const val serialVersionUID: Long = 1516128082115131137L
    }

}