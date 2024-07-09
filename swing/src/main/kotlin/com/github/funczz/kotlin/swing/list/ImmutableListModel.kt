package com.github.funczz.kotlin.swing.list

import javax.swing.AbstractListModel

class ImmutableListModel<T>(private val data: List<T>) : AbstractListModel<T>() {

    override fun getSize(): Int = data.size

    override fun getElementAt(p0: Int): T = data[p0]

}