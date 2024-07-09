package com.github.funczz.kotlin.swing.util

import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.awt.GraphicsEnvironment

class GraphicsEnvironmentExtTest {

    @Test
    fun virtualBounds() {
        val virtualBounds = GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .getVirtualWindowBounds()

        assertNotEquals(0, virtualBounds.width)
        assertNotEquals(0, virtualBounds.height)
    }

}
