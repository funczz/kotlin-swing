package com.github.funczz.kotlin.swing.dialog

import com.github.funczz.kotlin.swing.util.Gravity
import com.github.funczz.kotlin.swing.util.getFontMetricsStringBounds
import com.github.funczz.kotlin.swing.util.getLocation
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.geom.RoundRectangle2D
import java.util.concurrent.CompletableFuture
import javax.swing.*
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

@Suppress("Unused", "MemberVisibilityCanBePrivate")
class Toast(

    private val owner: JFrame,

    private val alphaMaximumValue: Int = 255,

    private val alphaMinimumValue: Int = 0, //完全に透明

    private val alphaFadeEachValue: Int = 10,

    fadeRefreshRate: Int = 25,

    private val windowRadius: Double = 0.0,

    ) {

    var background: Color = Color.DARK_GRAY

    var foreground: Color = Color.WHITE

    var font: Font = UIManager.get("ToolTip.font") as Font

    var duration: Int = LENGTH_SHORT

    var gravity: Gravity = Gravity.SOUTH

    var offsetX: Int = 0

    var offsetY: Int = 0

    var text: String = ""

    private var _completableFuture: CompletableFuture<Result<Unit>>? = null

    private var _text = JTextArea().also {
        it.isOpaque = true
        it.isEditable = false
    }

    private var _dialog: JDialog = JDialog(owner).also {
        it.layout = GridBagLayout()
        it.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent) {
                it.shape = RoundRectangle2D.Double(
                    0.0,
                    0.0,
                    it.width.toDouble(),
                    it.height.toDouble(),
                    windowRadius,
                    windowRadius
                )
            }
        })
        it.isAlwaysOnTop = true
        it.isUndecorated = true
        it.focusableWindowState = false
        it.modalityType = Dialog.ModalityType.MODELESS
        it.add(
            _text,
            GridBagConstraints(
                0,
                0,
                1,
                1,
                1.0,
                1.0,
                GridBagConstraints.CENTER,
                GridBagConstraints.BOTH,
                Insets(0, 0, 0, 0),
                0,
                0
            )
        )
    }

    private val _fadeInTimer = Timer(fadeRefreshRate, null as ActionListener?).also {
        it.isRepeats = true
        val fadeInActionListener = object : ActionListener {
            private var alpha = alphaMinimumValue
            override fun actionPerformed(e: ActionEvent) {
                alpha = min(alphaMaximumValue, alpha + alphaFadeEachValue)
                _text.background = createRGBA(background, alpha)
                if (alpha >= alphaMaximumValue) {
                    it.stop()
                }
            }
        }
        it.addActionListener(fadeInActionListener)
    }

    private val _fadeOutTimer = Timer(fadeRefreshRate, null as ActionListener?).also {
        it.isRepeats = true
        val fadeOutActionListener = object : ActionListener {
            private var alpha = alphaMaximumValue
            override fun actionPerformed(e: ActionEvent) {
                alpha = max(alphaMinimumValue, alpha - alphaFadeEachValue)
                _text.background = createRGBA(background, alpha)
                _text.repaint()
                _dialog.repaint()
                if (alpha <= alphaMinimumValue) {
                    it.stop()
                }
            }
        }
        it.addActionListener(fadeOutActionListener)
    }

    private val _width: Int
        get() {
            return ceil(_text.getFontMetricsStringBounds(_text.text).width).toInt()
        }

    private val _height: Int
        get() {
            return ceil(_text.getFontMetricsStringBounds(_text.text).height).toInt()
        }

    private val _location: Point
        get() {
            val dimension = Dimension(_width, _height)
            return owner.getLocation(dimension, gravity, offsetX, offsetY)
        }

    fun cancel(): Result<Boolean> = try {
        var result = false
        if (_completableFuture != null
            && !_completableFuture!!.isDone
        ) {
            _completableFuture!!.cancel(true)
            while (!_completableFuture!!.isDone) {
                Thread.sleep(10L)
            }
            _completableFuture = null
            result = true
        }
        initialize()
        Result.success(result)
    } catch (e: Throwable) {
        Result.failure(e)
    }

    fun show(): CompletableFuture<Result<Unit>> {
        return CompletableFuture.supplyAsync {
            try {
                cancel()
                createGUI()
                _dialog.isVisible = true
                _fadeInTimer.start()
                while (_fadeInTimer.isRunning) {
                    Thread.sleep(10L)
                }
                Thread.sleep(duration.toLong())
                _fadeOutTimer.start()
                while (_fadeOutTimer.isRunning) {
                    Thread.sleep(10L)
                }
                Result.success(Unit)
            } catch (e: Throwable) {
                Result.failure(e)
            } finally {
                initialize()
            }
        }.also {
            _completableFuture = it
        }
    }

    private fun initialize() {
        _fadeInTimer.stop()
        _fadeOutTimer.stop()
        _dialog.isVisible = false
        _dialog.dispose()
    }

    private fun createGUI() {
        _text.text = text
        _text.background = createRGBA(background, alphaMinimumValue)
        _text.foreground = foreground
        _text.font = font
        _dialog.setSize(_width, _height)
        _dialog.location = _location
    }

    private fun createRGBA(color: Color, alpha: Int): Color =
        Color(color.red, color.green, color.blue, alpha)

    companion object {
        const val LENGTH_SHORT = 3000
        const val LENGTH_LONG = 6000

        @JvmStatic
        @JvmOverloads
        fun makeText(
            owner: JFrame,
            text: String,
            duration: Int = LENGTH_SHORT,
            background: Color = Color.DARK_GRAY,
            gravity: Gravity = Gravity.SOUTH
        ): Toast {
            //if (duration != LENGTH_SHORT && duration != LENGTH_LONG) {
            //    throw IllegalArgumentException("not support the duration you have entered")
            //}
            return Toast(owner).also {
                it.text = text
                it.duration = duration
                it.background = background
                it.gravity = gravity
            }
        }

    }

}
