package demo.dialog

import com.github.funczz.kotlin.swing.util.Gravity
import javax.swing.ButtonGroup
import javax.swing.JRadioButton

class GravityButton(

    private val selection: Gravity? = null,

    private val text: (Gravity) -> String = { it.name },

    private val f: (Gravity) -> Unit = {}

) {

    private val center = JRadioButton().also {
        initializeButton(it, Gravity.CENTER.name)
    }

    private val north = JRadioButton().also {
        initializeButton(it, Gravity.NORTH.name)
    }

    private val northEast = JRadioButton().also {
        initializeButton(it, Gravity.NORTH_EAST.name)
    }

    private val east = JRadioButton().also {
        initializeButton(it, Gravity.EAST.name)
    }

    private val southEast = JRadioButton().also {
        initializeButton(it, Gravity.SOUTH_EAST.name)
    }

    private val south = JRadioButton().also {
        initializeButton(it, Gravity.SOUTH.name)
    }

    private val southWest = JRadioButton().also {
        initializeButton(it, Gravity.SOUTH_WEST.name)
    }

    private val west = JRadioButton().also {
        initializeButton(it, Gravity.WEST.name)
    }

    private val northWest = JRadioButton().also {
        initializeButton(it, Gravity.NORTH_WEST.name)
    }

    val buttons = listOf(
        center,
        north,
        northEast,
        east,
        southEast,
        south,
        southWest,
        west,
        northWest
    )

    private val buttonGroup = ButtonGroup().also {
        it.add(center)
        buttons.forEach { b ->
            it.add(b)
        }
    }

    private fun initializeButton(button: JRadioButton, name: String) {
        val gravity = Gravity.getEnum(name)
        if (selection != null && selection == gravity) {
            button.isSelected = true
        }
        button.name = gravity.name
        button.actionCommand = gravity.name
        button.text = text(gravity)
        button.addActionListener {
            f(gravity)
        }
    }
}