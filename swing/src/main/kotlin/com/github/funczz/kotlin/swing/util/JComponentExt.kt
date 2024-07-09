package com.github.funczz.kotlin.swing.util

import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.SwingUtilities

@Suppress("Unused")
fun JComponent.getOwner(): JFrame = SwingUtilities
    .getWindowAncestor(this) as JFrame
