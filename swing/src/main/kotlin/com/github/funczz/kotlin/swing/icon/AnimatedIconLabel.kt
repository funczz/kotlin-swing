package com.github.funczz.kotlin.swing.icon

import java.awt.event.HierarchyEvent
import javax.swing.JLabel
import javax.swing.Timer

class AnimatedIconLabel(

    /**
     * Animated Icon
     */
    private val icon: IAnimatedIcon,

    /**
     * milliseconds
     */
    delay: Int = 500,

    ) : JLabel() {

    private val animator = Timer(delay) {
        icon.next()
        revalidate()
        repaint()
    }

    init {
        setIcon(icon)
        addHierarchyListener { e ->
            if (e.changeFlags and HierarchyEvent.DISPLAYABILITY_CHANGED.toLong() != 0L) {
                if (e.component.isDisplayable) {
                    if (icon.isRunning()) animator.start()
                } else {
                    animator.stop()
                }
            } else if (e.changeFlags and HierarchyEvent.SHOWING_CHANGED.toLong() != 0L) {
                if (e.component.isShowing) {
                    if (icon.isRunning()) animator.start()
                } else {
                    animator.stop()
                }
            }
        }
    }

    fun startAnimation() {
        icon.setRunning(true)
        animator.start()
    }

    fun stopAnimation() {
        icon.setRunning(false)
        animator.stop()
    }

}