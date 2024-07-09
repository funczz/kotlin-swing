package demo

import java.awt.Container
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

fun JFrame.start(contentPane: Container) {
    SwingUtilities.invokeLater {
        this.contentPane = contentPane
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        pack()
        setLocationRelativeTo(null)
        isVisible = true
    }
}