package com.github.funczz.kotlin.swing.painter

import java.awt.Component
import java.awt.Graphics
import java.awt.Image

/**
 * 中央表示
 */
object CenterBgImagePainter : IBgImagePainter {

    override fun getName(): String = "center"

    override fun getInstance(): IBgImagePainter = this

    override fun paint(c: Component, g: Graphics, image: Image) {
        val imageWidth = image.getWidth(c)
        val imageHeight = image.getHeight(c)
        if (imageWidth <= 0 || imageHeight <= 0) {
            return
        }

        val size = c.size
        g.drawImage(image, (size.width - imageWidth) / 2, (size.height - imageHeight) / 2, c)
    }

}