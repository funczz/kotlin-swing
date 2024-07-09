package demo

import java.awt.GridBagConstraints
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.SwingConstants

class FunctionalActionPanel : AbstractGridBagJPanel() {

    private var counter = 0

    private val upButton = JButton("カウントアップ").also {
        it.addActionListener {
            counter += 1
            setText()
        }
    }

    private val clearButton = JButton("クリア").also {
        it.addActionListener {
            counter = 0
            setText()
        }
    }
    private val titleLabel = JLabel("カウント:")

    private val counterLabel = JLabel(counter.toString()).also {
        it.preferredSize = upButton.preferredSize
        it.horizontalAlignment = SwingConstants.LEFT
    }

    private fun setText() {
        counterLabel.text = counter.toString()
    }

    init {
        addComponent(
            component = titleLabel,
            gridx = 0,
            gridy = 0,
            anchor = GridBagConstraints.EAST,
            weightx = 0.0
        )
        addComponent(
            component = counterLabel,
            gridx = 1,
            gridy = 0,
            fill = GridBagConstraints.HORIZONTAL,
            anchor = GridBagConstraints.EAST,
            weightx = 1.0,
        )
        addComponent(
            component = upButton,
            gridx = 2,
            gridy = 0,
            anchor = GridBagConstraints.EAST,
            weightx = 0.0,
        )
        addComponent(
            component = clearButton,
            gridx = 3,
            gridy = 0,
            anchor = GridBagConstraints.EAST,
            weightx = 0.0,
        )
    }

}