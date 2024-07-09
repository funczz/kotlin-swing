package demo.layout

import com.github.funczz.kotlin.swing.layout.GridBagTableLayout
import java.awt.Dimension
import java.awt.GridBagConstraints.*
import javax.swing.*

class GridBagTableLayoutPanel : JPanel() {

    private val tab1 = JPanel().also {
        it.name = "tab-1"
        val label = JLabel("hello world in ${it.name}.")
        GridBagTableLayout().table(it) {
            tr {
                td(label) {
                    anchor = CENTER
                    fill = NONE
                }
                td(Box.createHorizontalBox()) {
                    fill = HORIZONTAL
                    weightx = 1.0
                }
            }
            tr {
                td(Box.createVerticalBox()) {
                    fill = VERTICAL
                    weighty = 1.0
                }
            }
        }
    }

    private val tab2 = JPanel().also {
        it.name = "tab-2"
        val label = JLabel("hello world in ${it.name}.")
        GridBagTableLayout().table(it) {
            tr {
                td(label) {
                    anchor = CENTER
                    fill = HORIZONTAL
                }
            }
            tr {
                td(Box.createVerticalBox()) {
                    fill = VERTICAL
                    weighty = 1.0
                }
            }
        }
    }

    private val tab3 = JPanel().also {
        it.name = "tab-3"
        val label = JLabel("hello world in ${it.name}.")
        GridBagTableLayout().table(it) {
            tr {
                td(Box.createHorizontalBox()) {
                    fill = HORIZONTAL
                    weightx = 1.0
                }
                td(label) {
                    anchor = CENTER
                    fill = NONE
                }
            }
            tr {
                td(Box.createVerticalBox()) {
                    fill = VERTICAL
                    weighty = 1.0
                }
            }
        }
    }

    private val tabbedPane = JTabbedPane().also {
        it.addTab(tab1.name, tab1)
        it.addTab(tab2.name, tab2)
        it.addTab(tab3.name, tab3)
    }

    init {
        GridBagTableLayout().table(this) {
            tr {
                td(tabbedPane) {
                    anchor = CENTER
                    fill = BOTH
                    gridwidth = 5
                    weightx = 1.0
                    weighty = 1.0
                }
            }
            tr {
                val cancelButton = JButton("キャンセル")
                val okButton = JButton("OK").also { it.preferredSize = cancelButton.preferredSize }
                td(Box.createHorizontalBox()) {
                    fill = HORIZONTAL
                    weightx = 0.49
                }
                td(okButton) {
                    anchor = CENTER
                    fill = NONE
                }
                td(Box.createHorizontalBox()) {
                    fill = HORIZONTAL
                    weightx = 0.02
                }
                td(cancelButton) {
                    anchor = CENTER
                    fill = NONE
                }
                td(Box.createHorizontalBox()) {
                    fill = HORIZONTAL
                    weightx = 0.49
                }
            }
        }
        preferredSize = Dimension(500, 200)
    }

}