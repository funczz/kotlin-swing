package com.github.funczz.kotlin.swing.painter

import java.awt.Component
import java.awt.Graphics
import java.awt.Image

/**
 * フィル表示
 */
object FillBgImagePainter : IBgImagePainter {

    override fun getName(): String = "fill"

    override fun getInstance(): IBgImagePainter = this

    override fun paint(c: Component, g: Graphics, image: Image) {
        val imageWidth = image.getWidth(c)
        val imageHeight = image.getHeight(c)
        val size = c.size
        if (imageWidth <= 0 || imageHeight <= 0) {
            return
        }

        val widthRatio = size.getWidth() / imageWidth.toDouble()
        val heightRatio = size.getHeight() / imageHeight.toDouble()
        val ratio = if (widthRatio < heightRatio) widthRatio else heightRatio

        val viewWidth = (imageWidth * ratio).toInt()
        val viewHeight = (imageHeight * ratio).toInt()
        g.drawImage(image, (size.width - viewWidth) / 2, (size.height - viewHeight) / 2, viewWidth, viewHeight, c)
    }

}