package com.github.funczz.kotlin.swing.util

import java.awt.Component
import java.awt.Font
import java.awt.geom.Rectangle2D

/**
 * https://stackoverflow.com/questions/368295/how-to-get-real-string-height-in-java
 */

@Suppress("Unused")
fun Component.getFontMetricsStringBounds(text: String, font: Font = this.font): Rectangle2D {
    val fontMetrics = this.getFontMetrics(font)
    return fontMetrics.getStringBounds(text, this.graphics)
}

@Suppress("Unused")
fun Component.getFontMetricsVisualBounds(text: String, font: Font = this.font): Rectangle2D {
    return font.createGlyphVector(this.getFontMetrics(font).fontRenderContext, text).visualBounds
}

