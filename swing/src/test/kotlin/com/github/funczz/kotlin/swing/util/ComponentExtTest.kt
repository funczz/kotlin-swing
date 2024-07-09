package com.github.funczz.kotlin.swing.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javax.swing.JPanel

class ComponentExtTest {

    @Test
    fun getFontMetricsStringBounds() {
        val bounds = JPanel().getFontMetricsStringBounds(text)
        assertEquals(42, Math.round(bounds.width))
        assertEquals(12, Math.round(bounds.height))
    }

    @Test
    fun getFontMetricsVisualBounds() {
        val bounds = JPanel().getFontMetricsVisualBounds(text)
        assertEquals(40, Math.round(bounds.width))
        assertEquals(10, Math.round(bounds.height))
    }

    private val text = "My Test"
}