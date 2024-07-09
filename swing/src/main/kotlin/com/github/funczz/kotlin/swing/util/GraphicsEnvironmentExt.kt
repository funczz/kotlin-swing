package com.github.funczz.kotlin.swing.util

import java.awt.GraphicsEnvironment
import java.awt.Rectangle

/**
 * マルチディスプレイのスクリーンサイズを取得する
 * シングルディスプレイの場合は
 * GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()
 */
@Suppress("Unused", "UnusedReceiverParameter")
fun GraphicsEnvironment.getVirtualWindowBounds(): Rectangle {
    var virtualBounds = Rectangle()
    val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
    val gs = ge.screenDevices
    for (j in gs.indices) {
        val gd = gs[j]
        val gc = gd.configurations
        for (i in gc.indices) {
            virtualBounds = virtualBounds.union(gc[i].bounds)
        }
    }
    return virtualBounds
}
