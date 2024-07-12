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

    var text: String = "",

    var duration: Long = LENGTH_SHORT,

    var gravity: Gravity = Gravity.SOUTH,

    var offsetX: Int = 0,

    var offsetY: Int = 0,

    var font: Font = DEFAULT_FONT,

    var foreground: Color = DEFAULT_FOREGROUND,

    var background: Color = DEFAULT_BACKGROUND,

    var windowRadius: Double = 0.0,

    var width: Int = -1,

    var height: Int = -1,

    var widthPadding: Int = 0,

    var heightPadding: Int = 0,

    var alphaMaximumValue: Int = 255,

    var alphaMinimumValue: Int = 0, //完全に透明

    var alphaFadeEachValue: Int = 10,

    var fadeRefreshRate: Int = 25,

    ) {

    private var _completableFuture: CompletableFuture<Result<Unit>>? = null

    private var _printArea = JLabel().also {
        it.isOpaque = true
        it.horizontalAlignment = SwingConstants.CENTER
        it.verticalAlignment = SwingConstants.CENTER
    }

    private var _dialog: JDialog = JDialog(owner)

    private val _fadeInActionListener = object : ActionListener {
        var alpha: Int = 0
        var function: () -> Unit = {}
        override fun actionPerformed(e: ActionEvent) {
            alpha = min(alphaMaximumValue, alpha + alphaFadeEachValue)
            _printArea.background = createRGBA(background, alpha)
            _printArea.repaint()
            _dialog.repaint()
            if (alpha >= alphaMaximumValue) {
                function()
            }
        }
    }

    private var _fadeInTimer = Timer(fadeRefreshRate, null as ActionListener?)

    private val _fadeOutActionListener = object : ActionListener {
        var alpha = alphaMaximumValue
        var function: () -> Unit = {}
        override fun actionPerformed(e: ActionEvent) {
            alpha = max(alphaMinimumValue, alpha - alphaFadeEachValue)
            _printArea.background = createRGBA(background, alpha)
            _printArea.repaint()
            _dialog.repaint()
            if (alpha <= alphaMinimumValue) {
                function()
            }
        }
    }

    private var _fadeOutTimer = Timer(fadeRefreshRate, null as ActionListener?)

    private val _width: Int
        get() {
            if (width >= 0) return width
            val orig = ceil(_printArea.getFontMetricsStringBounds(text.lines().maxBy { it.length }).width).toInt()
            return orig + (widthPadding * 2)
        }

    private val _height: Int
        get() {
            if (height >= 0) return height
            val orig = ceil(_printArea.getFontMetricsStringBounds(text).height).toInt() * text.lines().size
            return orig + (heightPadding * 2)
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
                _fadeInActionListener.alpha = alphaMinimumValue
                _fadeInTimer = createFadeInTimer()
                _fadeInTimer.start()
                while (_fadeInTimer.isRunning) {
                    Thread.sleep(10L)
                }
                Thread.sleep(duration)
                _fadeOutActionListener.alpha = alphaMaximumValue
                _fadeOutTimer = createFadeOutTimer()
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
        _printArea.text = createHtml(body = text)
        _printArea.background = createRGBA(background, alphaMinimumValue)
        _printArea.foreground = createRGBA(foreground, alphaMaximumValue)
        _printArea.font = font
        _dialog = createDialog()
        _dialog.location = _location
    }

    private fun createDialog(): JDialog {
        return JDialog(owner).also {
            it.setSize(_width, _height)
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
            it.layout = GridBagLayout()
            it.add(
                _printArea,
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
    }

    private fun createHtml(body: String): String {
        return "<html><body>%s</body></html>".format(body.lines().joinToString("<br />"))
    }

    private fun createRGBA(color: Color, alpha: Int): Color =
        Color(color.blue, color.green, color.red, alpha)
    //Color(color.red, color.green, color.blue, alpha) 色化け

    private fun createFadeInTimer(): Timer {
        return Timer(fadeRefreshRate, null as ActionListener?).also {
            it.isRepeats = true
            _fadeInActionListener.function = { it.stop() }
            it.addActionListener(_fadeInActionListener)
        }
    }

    private fun createFadeOutTimer(): Timer {
        return Timer(fadeRefreshRate, null as ActionListener?).also {
            it.isRepeats = true
            _fadeOutActionListener.function = { it.stop() }
            it.addActionListener(_fadeOutActionListener)
        }
    }

    companion object {

        val DEFAULT_FONT: Font = UIManager.getFont("ToolTip.font")

        val DEFAULT_FOREGROUND: Color = UIManager.getColor("ToolTip.foreground")

        val DEFAULT_BACKGROUND: Color = UIManager.getColor("ToolTip.background")

        const val LENGTH_SHORT: Long = 3000

        const val LENGTH_LONG: Long = 6000

        @JvmStatic
        @JvmOverloads
        fun makeText(
            owner: JFrame,
            text: String,
            duration: Long = LENGTH_SHORT,
            gravity: Gravity = Gravity.SOUTH
        ): Toast {
            //if (duration != LENGTH_SHORT && duration != LENGTH_LONG) {
            //    throw IllegalArgumentException("not support the duration you have entered")
            //}
            return Toast(
                owner = owner,
                text = text,
                duration = duration,
                gravity = gravity,
            )
        }

    }

}
