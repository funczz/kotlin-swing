package com.github.funczz.kotlin.swing.painter

import java.awt.Component
import java.awt.Graphics
import java.awt.Image

/**
 * デフォルト表示
 */
object DefaultBgImagePainter : IBgImagePainter {

    override fun getName(): String = "default"

    override fun getInstance(): IBgImagePainter = TileBgImagePainter

    override fun paint(c: Component, g: Graphics, image: Image) {
        throw NotImplementedError(message = "function `DefaultBgImagePainter::paint` is unimplemented.")
    }

}