package demo

import com.github.funczz.kotlin.swing.addFunctionalDocumentListener
import java.awt.Component
import java.awt.Dimension
import java.awt.GridBagConstraints
import javax.swing.JLabel
import javax.swing.JTextField
import javax.swing.SwingConstants

class FunctionalDocumentListenerPanel : AbstractGridBagJPanel() {

    private var prevValue = ""

    private val textField = JTextField().also { t ->
        t.horizontalAlignment = SwingConstants.LEFT
        t.document.addFunctionalDocumentListener(
            onInsertUpdate = {
                setText("Insert", t.text)
            },
            onRemoveUpdate = {
                setText("Remove", t.text)
            },
            onChangedUpdate = {
                setText("Changed", t.text)
            },
        )
    }

    private val label = JLabel("")

    private fun setText(eventName: String, newValue: String) {
        label.text = "%s: '%s' -> '%s'".format(eventName, prevValue, newValue)
        prevValue = newValue
    }

    private fun initialSize(component: Component, width: Int) {
        val height = component.getFontMetrics(this.font).height
        val dimension = Dimension(width, height)
        component.preferredSize = dimension
        component.minimumSize = dimension
    }

    init {
        initialSize(textField, 200)
        initialSize(label, 500)
        addComponent(
            component = textField,
            gridx = 0,
            gridy = 0,
            fill = GridBagConstraints.HORIZONTAL,
            anchor = GridBagConstraints.EAST,
            weightx = 2.0
        )
        addComponent(
            component = label,
            gridx = 1,
            gridy = 0,
            fill = GridBagConstraints.HORIZONTAL,
            anchor = GridBagConstraints.EAST,
            weightx = 5.0,
        )
    }

}