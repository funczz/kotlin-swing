package demo.icon

import com.github.funczz.kotlin.swing.icon.AnimatedIconLabel
import com.github.funczz.kotlin.swing.icon.CircleAnimatedIcon
import demo.AbstractGridBagJPanel
import java.awt.Color
import java.awt.GridBagConstraints
import javax.swing.JButton

class CircleAnimatedIconLabelPanel(ellipseColor: Color, iconSize: Int, delay: Int) : AbstractGridBagJPanel() {

    private val animatedIconLabel =
        AnimatedIconLabel(CircleAnimatedIcon(ellipseColor = ellipseColor, iconSize = iconSize), delay)

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
            weightx = 0.0
        )

        addComponent(
            component = startButton,
            gridx = 0,
            gridy = 1,
            anchor = GridBagConstraints.EAST,
            weightx = 1.0
        )
        addComponent(
            component = stopButton,
            gridx = 1,
            gridy = 1,
            anchor = GridBagConstraints.EAST,
            weightx = 0.0
        )
    }

}