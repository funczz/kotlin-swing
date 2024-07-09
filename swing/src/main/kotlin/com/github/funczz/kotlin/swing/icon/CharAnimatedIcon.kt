package com.github.funczz.kotlin.swing.icon

import java.awt.*
import java.util.*
import javax.swing.Icon
import javax.swing.JLabel

class CharAnimatedIcon(

    char: Char,

    repeat: Int = 5,

    private val color: Color = Color.BLACK,

    private val font: Font = JLabel().font,

    val size: Dimension = run {
        val fontMetrics = JLabel().getFontMetrics(font)
        Dimension(
            fontMetrics.stringWidth(String(arrayOf(char).toCharArray()).repeat(repeat)),
            fontMetrics.height,
        )
    },

    ) : Icon, IAnimatedIcon {


    private val _text = String(arrayOf(char).toCharArray())

    private val _repeat = 1.coerceAtLeast(repeat) - 1 //Math.max(1, repeat) - 1

    private var _counter = 1

    private var _running: Boolean = false


    override fun next() {
        if (_running) {
            _counter = if (_counter > _repeat) 1 else _counter + 1
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
        val s = _text.repeat(_counter)
        g2.font = font
        g2.color = color
        val fontMetrics = g2.fontMetrics
        g2.translate(x, y + fontMetrics.ascent)
        g2.drawString(s, 0, 0)

        g2.dispose()
    }

    override fun getIconWidth(): Int {
        return size.width
    }

    override fun getIconHeight(): Int {
        return size.height
    }

}