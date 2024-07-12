package com.github.funczz.kotlin.swing.icon

import java.awt.*
import java.util.*
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.UIManager

class TextAnimatedIcon(

    text: List<String>,

    private val color: Color = UIManager.getColor("Label.foreground"),

    private val font: Font = UIManager.getFont("Label.font"),

    val size: Dimension = run {
        val fontMetrics = JLabel().getFontMetrics(font)
        Dimension(
            fontMetrics.stringWidth(text.maxBy { it.length }),
            fontMetrics.height,
        )
    },

    ) : Icon, IAnimatedIcon {

    private val _text: MutableList<String> = text.toMutableList()

    private var _running: Boolean = false

    override fun next() {
        if (_running) {
            Collections.rotate(_text, -1)
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
        val s = _text.first()
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