package demo

import com.github.funczz.kotlin.swing.addFunctionalMouseListener
import java.awt.Component
import java.awt.Cursor
import java.awt.Dimension
import java.awt.GridBagConstraints
import javax.swing.JLabel

class FunctionalMouseAdapterPanel : AbstractGridBagJPanel() {

    private val label = JLabel("このパネルの上でマウスを右クリックして下さい。マウスカーソルが手の形に変わります。")

    private val status = JLabel("")

    private fun initialSize(component: Component, @Suppress("SameParameterValue") width: Int) {
        val height = component.getFontMetrics(this.font).height
        val dimension = Dimension(width, height)
        component.preferredSize = dimension
        component.minimumSize = dimension
    }

    init {
        initialSize(status, 20)
        addComponent(
            component = label,
            gridx = 0,
            gridy = 0,
            fill = GridBagConstraints.HORIZONTAL,
            anchor = GridBagConstraints.EAST,
        )
        addComponent(
            component = status,
            gridx = 0,
            gridy = 1,
            fill = GridBagConstraints.HORIZONTAL,
            anchor = GridBagConstraints.EAST,
        )

        addFunctionalMouseListener(
            onMousePressed = {
                val cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                val source = it.source as Component
                source.cursor = cursor
            },
            onMouseReleased = {
                val cursor = Cursor.getDefaultCursor()
                val source = it.source as Component
                source.cursor = cursor
            },
            onMouseClicked = {
                val text = "button=%s click=%d".format(it.button, it.clickCount)
                status.text = text
            }
        )
    }

}