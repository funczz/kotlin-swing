package com.github.funczz.kotlin.swing.util

@Suppress("Unused")
enum class Gravity {
    CENTER,
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    companion object {

        @JvmStatic
        fun getEnum(name: String): Gravity {
            return when (name) {
                CENTER.name -> CENTER
                NORTH.name -> NORTH
                NORTH_EAST.name -> NORTH_EAST
                EAST.name -> EAST
                SOUTH_EAST.name -> SOUTH_EAST
                SOUTH.name -> SOUTH
                SOUTH_WEST.name -> SOUTH_WEST
                WEST.name -> WEST
                NORTH_WEST.name -> NORTH_WEST
                else -> throw IllegalArgumentException("Gravity name mismatch: %s".format(name))
            }
        }

    }
}
