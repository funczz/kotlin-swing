package com.github.funczz.kotlin.swing.painter

import java.awt.Component
import java.awt.Graphics
import java.awt.Image

interface IBgImagePainter {

    fun getName(): String

    fun getInstance(): IBgImagePainter

    /**
     * コンポーネントに描画する。
     * @param c 描画対象コンポーネント
     * @param g Graphics
     * @param image 描画イメージ
     */
    fun paint(c: Component, g: Graphics, image: Image)

}