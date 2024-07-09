package demo.list

import demo.AbstractGridBagJPanel
import java.awt.Color
import java.awt.Dimension
import java.awt.GridBagConstraints
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JTextArea

class JPanelListElementPanel(

    private val number: Int

) : AbstractGridBagJPanel() {

    private val title = JLabel("$number").also {
        it.background = Color.WHITE
        val dimension = Dimension(50, it.preferredSize.height)
        it.preferredSize = dimension
        it.minimumSize = dimension
    }

    private val subject = JTextArea().also {
        it.isEditable = false
        it.background = Color.WHITE
        it.append("The quick brown fox jumps over the lazy dog.")
        it.append("\n")
        it.append("How razorback-jumping frogs can level six piqued gymnasts! ")
    }

    private val button = JButton("OK").also {
        it.addActionListener {
            JOptionPane.showMessageDialog(
                this,
                "$number",
                "OK",
                JOptionPane.INFORMATION_MESSAGE
            )

        }
    }

    init {
        background = Color.WHITE

        addComponent(
            component = title,
            gridx = 0,
            gridy = 0,
            fill = GridBagConstraints.HORIZONTAL,
            anchor = GridBagConstraints.EAST,
            weightx = 1.0
        )
        addComponent(
            component = subject,
            gridx = 0,
            gridy = 1,
            fill = GridBagConstraints.BOTH,
            anchor = GridBagConstraints.EAST,
            weightx = 1.0
        )
        addComponent(
            component = button,
            gridx = 1,
            gridy = 0,
            gridheight = 2,
        )
        println("Created JPanelListElementPanel. number=$number preferredSize=$preferredSize")
    }

    companion object {
        private const val serialVersionUID: Long = 1814816584549486351L
    }

}
