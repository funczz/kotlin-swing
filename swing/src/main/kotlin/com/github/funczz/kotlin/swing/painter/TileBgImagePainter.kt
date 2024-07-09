package com.github.funczz.kotlin.swing.painter

import java.awt.Component
import java.awt.Graphics
import java.awt.Image
import java.awt.Rectangle

/**
 * タイル表示
 */
object TileBgImagePainter : IBgImagePainter {

    override fun getName(): String = "tile"

    override fun getInstance(): IBgImagePainter = this

    private var rect = Rectangle()

    override fun paint(c: Component, g: Graphics, image: Image) {
        val imageWidth = image.getWidth(c)
        val imageHeight = image.getHeight(c)
        if (imageWidth <= 0 || imageHeight <= 0) {
            return
        }

        val size = c.size
        val clip = g.clip

        var y = 0
        while (y < size.getHeight()) {
            var x = 0
            while (x < size.getWidth()) {
                rect.setBounds(x, y, imageWidth, imageHeight)
                if (clip.intersects(rect)) {
                    g.drawImage(image, x, y, c)
                }
                x += imageWidth
            }
            y += imageHeight
        }
    }

}