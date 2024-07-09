package com.github.funczz.kotlin.swing.layout

import java.awt.Component
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JComponent

@Suppress("Unused")
class GridBagTableLayout : GridBagLayout() {

    val table = Table()

    fun table(function: Table.() -> Unit): GridBagTableLayout {
        table.function()
        return this
    }

    fun table(jComponent: JComponent, isSetLayout: Boolean = true) {
        if (isSetLayout) jComponent.layout = this
        // gridx Component を配置するグリッド上の横の座標を 0 から指定する
        // gridy Component を配置するグリッド上の縦の座標を 0 から指定する
        table.get().forEachIndexed { row, tr ->
            tr.get().forEachIndexed { column, td ->
                val gridBagConstraints = GridBagConstraints().also {
                    it.gridx = column
                    it.gridy = row
                    it.gridheight = td.gridheight
                    it.gridwidth = td.gridwidth
                    it.fill = td.fill
                    it.insets = td.insets
                    it.anchor = td.anchor
                    it.weightx = td.weightx
                    it.weighty = td.weighty
                }
                jComponent.add(td.component, gridBagConstraints)
            }
        }
    }

    fun table(jComponent: JComponent, isSetLayout: Boolean = true, function: Table.() -> Unit) {
        table(function)
        table(jComponent, isSetLayout)
    }

    @DslMarker
    annotation class GridBagTableLayoutDslMarker

    @GridBagTableLayoutDslMarker
    inner class Table {
        val layout = this@GridBagTableLayout

        private var tr: MutableList<Tr> = mutableListOf()

        fun tr(function: Tr.() -> Unit) {
            val tr = Tr()
            tr.function()
            add(tr)
        }

        fun add(tr: Tr) {
            this.tr.add(tr)
        }

        fun clear() {
            tr.clear()
        }

        fun get(): List<Tr> {
            return tr.toList()
        }

        override fun toString(): String {
            return "Table(%s)".format(tr.joinToString(", "))
        }

    }

    @GridBagTableLayoutDslMarker
    class Tr {

        private var td: MutableList<Td> = mutableListOf()

        fun td(component: Component, function: Td.() -> Unit) {
            val td = Td(component)
            td.function()
            add(td)
        }

        fun add(td: Td) {
            this.td.add(td)
        }

        fun clear() {
            td.clear()
        }

        fun get(): List<Td> {
            return td.toList()
        }

        override fun toString(): String {
            return "Tr(%s)".format(td.joinToString(", "))
        }

    }

    /**
     * @param component グリッド上に配置する Component
     */
    @GridBagTableLayoutDslMarker
    class Td(val component: Component) {

        /**
         * Component が占めるグリッド枠の縦の数を指定する: デフォルトは 1
         */
        var gridheight: Int = 1

        /**
         * Component が占めるグリッド枠の横の数を指定する: デフォルトは 1
         */
        var gridwidth: Int = 1

        /**
         * Component をグリッド枠いっぱいに広げるかどうかを指定する(NONE, VERTICAL, HORIZONTAL, BOTH):
         * デフォルトは NONE
         */
        var fill: Int = GridBagConstraints.NONE

        /**
         * Component の top, left, bottom, right 間隔を指定する: デフォルトはすべて 0
         */
        var insets: Insets = Insets(0, 0, 0, 0)

        /**
         * Component をどこに寄せるかを指定する(CENTER, NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST):
         * デフォルトは CENTER
         */
        var anchor: Int = GridBagConstraints.CENTER

        /**
         * Component の間隔を分配する横の比率を指定する。この指定は行・列単位で有効となる: デフォルトは 0.0
         */
        var weightx: Double = 0.0

        /**
         * Component の間隔を分配する縦の比率を指定する。この指定は行・列単位で有効となる: デフォルトは 0.0
         */
        var weighty: Double = 0.0

        override fun toString(): String {
            return "Td(component=%s)".format(component)
        }
    }

}