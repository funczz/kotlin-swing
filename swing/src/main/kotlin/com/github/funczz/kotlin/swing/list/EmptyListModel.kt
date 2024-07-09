package com.github.funczz.kotlin.swing.list

import javax.swing.AbstractListModel

class EmptyListModel<T> : AbstractListModel<T>() {

    override fun getSize(): Int = 0

    override fun getElementAt(p0: Int): T = throw NoSuchElementException(
        "This model is always empty."
    )

}