package demo.painter

import com.github.funczz.kotlin.swing.painter.CenterBgImagePainter
import com.github.funczz.kotlin.swing.painter.FillBgImagePainter
import com.github.funczz.kotlin.swing.painter.IBgImagePainter
import com.github.funczz.kotlin.swing.painter.TileBgImagePainter
import demo.AbstractGridBagJPanel
import java.awt.Graphics
import java.awt.GridBagConstraints
import java.awt.Image
import javax.imageio.ImageIO
import javax.swing.Box
import javax.swing.ButtonGroup
import javax.swing.JRadioButton

@Suppress("Unused")
class BgImagePainterPanel : AbstractGridBagJPanel() {

    private val bgImage: Image = ClassLoader
        .getSystemClassLoader()
        .getResourceAsStream("coffee-512x512.png")
        .let {
            ImageIO.read(it!!)
        }
        .also {
            it.flush()
        }

    private var bgImagePainter: IBgImagePainter

    private val centerRadioButton = JRadioButton("Center").also {
        it.addActionListener {
            setBgImagePainter(CenterBgImagePainter)
        }
    }
    private val fillRadioButton = JRadioButton("Fill").also {
        it.addActionListener {
            setBgImagePainter(FillBgImagePainter)
        }
    }
    private val tileRadioButton = JRadioButton("Tile").also {
        it.addActionListener {
            setBgImagePainter(TileBgImagePainter)
        }
    }

    private val bgImagePainterButtonGroup = ButtonGroup().also {
        it.add(centerRadioButton)
        it.add(fillRadioButton)
        it.add(tileRadioButton)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        bgImagePainter.paint(this, g, bgImage)

    }

    private fun setBgImagePainter(painter: IBgImagePainter) {
        bgImagePainter = painter
        revalidate()
        repaint()
    }

    init {
        bgImagePainter = CenterBgImagePainter
        centerRadioButton.isSelected = true

        addComponent(
            component = centerRadioButton,
            gridx = 0,
            gridy = 0,
            anchor = GridBagConstraints.NORTH
        )
        addComponent(
            component = fillRadioButton,
            gridx = 1,
            gridy = 0,
            anchor = GridBagConstraints.NORTH
        )
        addComponent(
            component = tileRadioButton,
            gridx = 3,
            gridy = 0,
            anchor = GridBagConstraints.NORTH
        )
        addComponent(
            component = Box.createVerticalGlue(),
            gridx = 0,
            gridy = 1,
            fill = GridBagConstraints.BOTH,
            weighty = 1.0
        )
    }

    companion object {
        private const val serialVersionUID: Long = 78667866782083192L
    }

}