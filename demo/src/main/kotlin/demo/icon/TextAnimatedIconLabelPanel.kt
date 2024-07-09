package demo.icon

import com.github.funczz.kotlin.swing.icon.AnimatedIconLabel
import com.github.funczz.kotlin.swing.icon.TextAnimatedIcon
import demo.AbstractGridBagJPanel
import java.awt.Color
import java.awt.GridBagConstraints
import javax.swing.JButton

class TextAnimatedIconLabelPanel(color: Color, delay: Int = 500) :
    AbstractGridBagJPanel() {

    private val text = listOf(
        "now loading.",
        "now loading..",
        "now loading...",
        "now loading....",
        "now loading.....",
    )

    private val animatedIconLabel = AnimatedIconLabel(
        TextAnimatedIcon(text = text, color = color),
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
            component = animatedIconLabel,
            gridx = 0,
            gridy = 0,
            gridwidth = 2,
            anchor = GridBagConstraints.CENTER,
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