package demo.icon

import com.github.funczz.kotlin.swing.icon.AnimatedIconLabel
import com.github.funczz.kotlin.swing.icon.CharAnimatedIcon
import demo.AbstractGridBagJPanel
import java.awt.Color
import java.awt.GridBagConstraints
import javax.swing.JButton
import javax.swing.JLabel

class CharAnimatedIconLabelPanel(char: Char, repeat: Int, color: Color, delay: Int = 500) :
    AbstractGridBagJPanel() {

    private val titleLabel = JLabel("now loading")

    private val animatedIconLabel = AnimatedIconLabel(
        CharAnimatedIcon(char = char, repeat = repeat, color = color),
        delay
    )

    private val startButton = JButton("Start")

    private val stopButton = JButton("Stop")

    init {
        startButton.also {
            it.addActionListener { _ ->
                animatedIconLabel.startAnimation()
                it.isEnabled = false
                stopButton.isEnabled = true
            }
        }

        stopButton.also {
            it.isEnabled = false
            it.addActionListener { _ ->
                animatedIconLabel.stopAnimation()
                it.isEnabled = false
                startButton.isEnabled = true
            }
        }
    }

    init {
        addComponent(
            component = titleLabel,
            gridx = 0,
            gridy = 0,
            gridwidth = 2,
            anchor = GridBagConstraints.WEST,
        )
        addComponent(
            component = animatedIconLabel,
            gridx = 1,
            gridy = 0,
            gridwidth = 2,
            anchor = GridBagConstraints.EAST,
        )

        addComponent(
            component = startButton,
            gridx = 0,
            gridy = 1,
            anchor = GridBagConstraints.EAST,
        )
        addComponent(
            component = stopButton,
            gridx = 1,
            gridy = 1,
            anchor = GridBagConstraints.EAST,
        )
    }

}