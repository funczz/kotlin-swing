package com.github.funczz.kotlin.swing

import java.awt.Component
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseWheelEvent

@Suppress("Unused")
open class FunctionalMouseAdapter(
    private val onMouseClicked: (MouseEvent) -> Unit = {},
    private val onMouseDragged: (MouseEvent) -> Unit = {},
    private val onMouseEntered: (MouseEvent) -> Unit = {},
    private val onMouseExited: (MouseEvent) -> Unit = {},
    private val onMouseMoved: (MouseEvent) -> Unit = {},
    private val onMousePressed: (MouseEvent) -> Unit = {},
    private val onMouseReleased: (MouseEvent) -> Unit = {},
    private val onMouseWheelMoved: (MouseEvent) -> Unit = {},
) : MouseAdapter() {

    override fun mouseClicked(e: MouseEvent) {
        onMouseClicked(e)
    }

    override fun mouseDragged(e: MouseEvent) {
        onMouseDragged(e)
    }

    override fun mouseEntered(e: MouseEvent) {
        onMouseEntered(e)
    }

    override fun mouseExited(e: MouseEvent) {
        onMouseExited(e)
    }

    override fun mouseMoved(e: MouseEvent) {
        onMouseMoved(e)
    }

    override fun mousePressed(e: MouseEvent) {
        onMousePressed(e)
    }

    override fun mouseReleased(e: MouseEvent) {
        onMouseReleased(e)
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        onMouseWheelMoved(e)
    }

}

fun Component.addFunctionalMouseListener(
    onMouseClicked: (MouseEvent) -> Unit = {},
    onMouseDragged: (MouseEvent) -> Unit = {},
    onMouseEntered: (MouseEvent) -> Unit = {},
    onMouseExited: (MouseEvent) -> Unit = {},
    onMouseMoved: (MouseEvent) -> Unit = {},
    onMousePressed: (MouseEvent) -> Unit = {},
    onMouseReleased: (MouseEvent) -> Unit = {},
    onMouseWheelMoved: (MouseEvent) -> Unit = {},
) {
    addMouseListener(
        FunctionalMouseAdapter(
            onMouseClicked,
            onMouseDragged,
            onMouseEntered,
            onMouseExited,
            onMouseMoved,
            onMousePressed,
            onMouseReleased,
            onMouseWheelMoved
        )
    )
}