package com.github.funczz.kotlin.swing.util

import java.awt.Dimension
import java.awt.Point
import javax.swing.JFrame

@Suppress("Unused")
fun JFrame.getLocation(dimension: Dimension, gravity: Gravity, offsetX: Int, offsetY: Int): Point {
    val (x, y) = when (gravity) {
        Gravity.CENTER -> Pair(this.getCenter(dimension.width), this.getMiddle(dimension.height))
        Gravity.NORTH -> Pair(this.getCenter(dimension.width), this.getTop())
        Gravity.NORTH_EAST -> Pair(this.getRight(dimension.width), this.getTop())
        Gravity.EAST -> Pair(this.getRight(dimension.width), this.getMiddle(dimension.height))
        Gravity.SOUTH_EAST -> Pair(this.getRight(dimension.width), this.getBottom(dimension.height))
        Gravity.SOUTH -> Pair(this.getCenter(dimension.width), this.getBottom(dimension.height))
        Gravity.SOUTH_WEST -> Pair(this.getLeft(), this.getBottom(dimension.height))
        Gravity.WEST -> Pair(this.getLeft(), this.getMiddle(dimension.height))
        Gravity.NORTH_WEST -> Pair(this.getLeft(), this.getTop())
    }
    return Point(x + offsetX, y + offsetY)
}

@Suppress("Unused")
fun JFrame.getLeft(): Int {
    return this.location.x + this.insets.left
}

@Suppress("Unused")
fun JFrame.getCenter(width: Int): Int {
    return this.location.x + ((this.width - width) / 2)
}

@Suppress("Unused")
fun JFrame.getRight(width: Int): Int {
    return this.location.x + this.width - width - this.insets.right
}

@Suppress("Unused")
fun JFrame.getTop(): Int {
    return this.location.y + this.insets.top
}

@Suppress("Unused")
fun JFrame.getMiddle(height: Int): Int {
    val top = this.insets.top
    val bottom = this.insets.bottom
    return this.location.y + ((this.height - height - top - bottom) / 2) + top
}

@Suppress("Unused")
fun JFrame.getBottom(height: Int): Int {
    return this.location.y + this.height - height - this.insets.bottom
}

