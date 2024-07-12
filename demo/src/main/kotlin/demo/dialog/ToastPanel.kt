package demo.dialog

import com.github.funczz.kotlin.swing.addFunctionalDocumentListener
import com.github.funczz.kotlin.swing.dialog.Toast
import com.github.funczz.kotlin.swing.util.Gravity
import com.github.funczz.kotlin.swing.util.getFontMetricsStringBounds
import com.github.funczz.kotlin.swing.util.getOwner
import demo.AbstractGridBagJPanel
import java.awt.*
import javax.swing.*
import javax.swing.border.EtchedBorder
import kotlin.math.ceil

class ToastPanel : AbstractGridBagJPanel() {

    private var gravity = Gravity.SOUTH

    private var offsetX = 0

    private var offsetY = 0

    private val gravityButtonPanel = JPanel(FlowLayout()).also {
        val gravityButton = GravityButton(selection = Gravity.SOUTH) { g ->
            gravity = g
        }
        gravityButton.buttons.forEach { b ->
            it.add(b)
        }
        it.border = EtchedBorder(EtchedBorder.LOWERED)
    }

    private val offsetFieldPanel = JPanel().also {
        val x = JTextField("0").also { field ->
            val color = field.foreground
            field.document.addFunctionalDocumentListener { _ ->
                try {
                    offsetX = field.text.toInt()
                    field.foreground = color
                } catch (e: NumberFormatException) {
                    field.foreground = Color.RED
                }
            }
        }
        val y = JTextField("0").also { field ->
            val color = field.foreground
            field.document.addFunctionalDocumentListener { _ ->
                try {
                    offsetY = field.text.toInt()
                    field.foreground = color
                } catch (e: NumberFormatException) {
                    field.foreground = Color.RED
                }
            }
        }
        val boxWidth = ceil(x.getFontMetricsStringBounds("999").width).toInt()
        it.layout = BoxLayout(it, BoxLayout.X_AXIS)
        it.add(JLabel("Offset X "))
        it.add(x)
        it.add(Box.createRigidArea(Dimension(boxWidth, 0)))
        it.add(JLabel("Offset Y "))
        it.add(y)
    }

    private val jTextField = JTextArea().also {
        it.text = "hello world."
    }

    private val jScrollPane = JScrollPane(jTextField).also {
        val dimension = Dimension(200, it.preferredSize.height * 5)
        it.preferredSize = dimension
    }

    private val jButton = JButton("通知").also {
        it.addActionListener { _ ->
            val text = jTextField.text
            if (text.isBlank()) return@addActionListener

            Toast(
                owner = it.getOwner(),
                text = jTextField.text,
                duration = Toast.LENGTH_SHORT,
                gravity = gravity,
                offsetX = offsetX,
                offsetY = offsetY,
                font = Font(Toast.DEFAULT_FONT.fontName, Font.PLAIN, font.size * 2),
                //foreground = Color.WHITE,
                //background = Color.BLUE,
                windowRadius = 15.0,
                widthPadding = 10,
                heightPadding = 10,
            ).show()
        }
    }

    init {
        addComponent(
            component = jScrollPane,
            gridx = 0,
            gridy = 0,
            fill = GridBagConstraints.BOTH,
            weightx = 1.0,
            weighty = 1.0,
        )
        addComponent(
            component = jButton,
            gridx = 1,
            gridy = 0,
            fill = GridBagConstraints.NONE,
            anchor = GridBagConstraints.EAST,
        )
        addComponent(
            component = gravityButtonPanel,
            gridx = 0,
            gridy = 1,
            gridwidth = 2,
            fill = GridBagConstraints.HORIZONTAL,
            weightx = 1.0,
        )
        addComponent(
            component = offsetFieldPanel,
            gridx = 0,
            gridy = 2,
            gridwidth = 2,
            fill = GridBagConstraints.HORIZONTAL,
            weightx = 1.0,
        )
        addComponent(
            component = Box.createVerticalStrut(gravityButtonPanel.preferredSize.height),
            gridx = 0,
            gridy = 3,
            fill = GridBagConstraints.HORIZONTAL,
        )
    }

}