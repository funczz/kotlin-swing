package com.github.funczz.kotlin.swing

import javax.swing.SwingWorker

@Suppress("Unused")
open class FunctionalSwingWorker<T, V>(
    private val onDoInBackground: ((V) -> Unit) -> T,
    private val onProcess: (List<V>) -> Unit,
    private val onDone: (Result<T>) -> Unit
) : SwingWorker<T, V>() {

    override fun doInBackground(): T = onDoInBackground(this::publish)

    override fun process(chunks: List<V>) = onProcess(chunks)

    override fun done() {
        val result = try {
            Result.success(get())
        } catch (e: Throwable) {
            Result.failure(e)
        }
        onDone(result)
    }

}