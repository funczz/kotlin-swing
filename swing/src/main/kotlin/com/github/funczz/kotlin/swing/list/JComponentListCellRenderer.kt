package com.github.funczz.kotlin.swing.list

import java.awt.Color
import java.awt.Component
import javax.swing.JComponent
import javax.swing.JList
import javax.swing.ListCellRenderer
import javax.swing.UIManager

/**
 * https://stackoverflow.com/questions/7107939/add-jpanel-to-jlist
 */
class JComponentListCellRenderer<T : JComponent>(

    selectionBackground: Color? = null,

    ) : ListCellRenderer<T> {

    private val selectionBackground = selectionBackground
        ?: UIManager.getColor("List.selectionBackground")

    override fun getListCellRendererComponent(
        list: JList<out T>,
        renderer: T,
        index: Int,
        isSelected: Boolean,
        cellHasFocus: Boolean
    ): Component {
        renderer.background = if (isSelected) selectionBackground else list.background
        return renderer
    }

}