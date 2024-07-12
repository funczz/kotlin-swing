package com.github.funczz.kotlin.swing.util

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import javax.swing.UIManager

class UIManagerTest {

    @Test
    fun getDefaults() {
        UIManager
            .getDefaults()
            .map { (k, v) -> "$k=$v" }
            .sorted()
            .forEach(::println)
    }

    @Test
    fun `getFont Label#font`() {
        assertDoesNotThrow {
            UIManager.getFont("Label.font").fontName
        }
    }

    @Test
    fun `getColor Label#foreground`() {
        assertDoesNotThrow {
            UIManager.getColor("Label.foreground").alpha
        }
    }

    @Test
    fun `getFont ToolTip#font`() {
        assertDoesNotThrow {
            UIManager.getFont("ToolTip.font").fontName
        }
    }

    @Test
    fun `getColor ToolTip#foreground`() {
        assertDoesNotThrow {
            UIManager.getColor("ToolTip.foreground").alpha
        }
    }

    @Test
    fun `getColor ToolTip#background`() {
        assertDoesNotThrow {
            UIManager.getColor("ToolTip.background").alpha
        }
    }
}