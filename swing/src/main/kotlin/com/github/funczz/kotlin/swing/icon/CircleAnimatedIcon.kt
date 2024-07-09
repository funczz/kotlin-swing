package com.github.funczz.kotlin.swing.icon

import java.awt.*
import java.awt.geom.Ellipse2D
import java.util.*

class CircleAnimatedIcon(

    private val ellipseColor: Color = Color(0x80_80_80),

    iconSize: Int = 1,

    ) : IAnimatedIcon {

    private val _r = 2.0 * iconSize
    private val _sx = 1.0 * iconSize
    private val _sy = 1.0 * iconSize

    private val _width = (_r * 8 + _sx * 2).toInt()

    private val _height = (_r * 8 + _sy * 2).toInt()

    private val _list: MutableList<Shape> = mutableListOf(
        Ellipse2D.Double(_sx + 1 * _r, _sy + 1 * _r, 2 * _r, 2 * _r),
        Ellipse2D.Double(_sx + 0 * _r, _sy + 3 * _r, 2 * _r, 2 * _r),
        Ellipse2D.Double(_sx + 1 * _r, _sy + 5 * _r, 2 * _r, 2 * _r),
        Ellipse2D.Double(_sx + 3 * _r, _sy + 6 * _r, 2 * _r, 2 * _r),
        Ellipse2D.Double(_sx + 5 * _r, _sy + 5 * _r, 2 * _r, 2 * _r),
        Ellipse2D.Double(_sx + 6 * _r, _sy + 3 * _r, 2 * _r, 2 * _r),
        Ellipse2D.Double(_sx + 5 * _r, _sy + 1 * _r, 2 * _r, 2 * _r),
        Ellipse2D.Double(_sx + 3 * _r, _sy + 0 * _r, 2 * _r, 2 * _r)
    )

    private var _running: Boolean = false

    override fun next() {
        if (_running) {
            Collections.rotate(_list, 1)
        }
    }

    override fun isRunning(): Boolean = _running

    override fun setRunning(running: Boolean) {
        _running = running
    }

    override fun paintIcon(c: Component, g: Graphics, x: Int, y: Int) {
        val g2 = g.create() as Graphics2D

        g2.translate(x, y)
        g2.paint = Optional.ofNullable(c).map(Component::getBackground).orElse(Color.WHITE)
        g2.fillRect(0, 0, iconWidth, iconHeight)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2.paint = ellipseColor
        val size = _list.size.toFloat()
        _list.forEach { s ->
            val alpha = if (_running) (_list.size - _list.indexOf(s)) / size else .5f
            g2.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha)
            g2.fill(s)
        }

        g2.dispose()
    }

    override fun getIconWidth(): Int {
        return _width
    }

    override fun getIconHeight(): Int {
        return _height
    }

}