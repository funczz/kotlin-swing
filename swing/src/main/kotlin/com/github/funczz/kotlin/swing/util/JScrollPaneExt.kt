package com.github.funczz.kotlin.swing.util

import java.awt.Dimension
import java.awt.GraphicsEnvironment
import javax.swing.JScrollPane
import javax.swing.ScrollPaneConstants

@Suppress("Unused", "UnusedReceiverParameter")
fun JScrollPane.getScrollBarSize(): Dimension {
    val rectangle = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .maximumWindowBounds
    val dimension = Dimension(rectangle.width, rectangle.height)
    val pane = JScrollPane(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS,
    ).also {
        it.size = dimension
        it.doLayout()
    }
    return Dimension(
        dimension.width - pane.viewport.width,
        dimension.height - pane.viewport.height
    )
}
