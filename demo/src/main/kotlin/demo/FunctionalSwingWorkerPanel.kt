package demo

import com.github.funczz.kotlin.swing.FunctionalSwingWorker
import java.awt.Color
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.util.concurrent.CancellationException
import javax.swing.*

class FunctionalSwingWorkerPanel : AbstractGridBagJPanel() {

    private val textArea = JTextArea().apply {
        name = "textArea"
        isEditable = false
        background = Color.WHITE
    }

    private val textAreaScrollPane = JScrollPane(textArea)

    private val progressBar = JProgressBar()

    private val startButton = JButton().apply {
        name = "startButton"
        text = "Start"
        isEnabled = true
        addActionListener {
            startWorker()
        }
    }

    private val stopButton = JButton().apply {
        name = "stopButton"
        text = "Stop"
        isEnabled = false
        addActionListener {
            stopWorker()
        }
    }

    private val lengthOfWorker = 200

    private var worker: FunctionalSwingWorker<Boolean, Int>? = null

    private fun startWorker() {
        textArea.text = ""
        startButton.isEnabled = false
        stopButton.isEnabled = true
        progressBar.value = 0
        progressBar.isIndeterminate = true
        createWorker()
        worker!!.execute()
    }

    private fun stopWorker() {
        worker?.also {
            if (!it.isDone) it.cancel(true)
        }
        startButton.isEnabled = true
        stopButton.isEnabled = false
        progressBar.isIndeterminate = false
        worker = null
    }

    private fun createWorker() {
        worker = FunctionalSwingWorker(
            onDoInBackground = {
                try {
                    progressBar.isIndeterminate = true
                    Thread.sleep(2000)
                    progressBar.isIndeterminate = false
                    for (i in 0..lengthOfWorker) {
                        Thread.sleep(50)
                        it(i)
                    }
                    true
                } catch (e: InterruptedException) {
                    false
                }
            },
            onProcess = {
                if (worker == null) return@FunctionalSwingWorker
                it.forEach { s ->
                    textArea.append(String.format("%03d\n", s))
                    progressBar.value = (100 * s / lengthOfWorker)
                }
            },
            onDone = {
                val result = try {
                    if (it.getOrThrow()) "SUCCESSFUL." else "FAILED."
                } catch (e: CancellationException) {
                    "CANCELLED."
                }
                textArea.append(result)
                startButton.isEnabled = true
                stopButton.isEnabled = false
            }
        )
    }

    init {
        preferredSize = Dimension(300, 500)
        addComponent(
            component = textAreaScrollPane,
            gridx = 0,
            gridy = 0,
            gridwidth = 4,
            fill = GridBagConstraints.BOTH,
            weightx = 1.0,
            weighty = 1.0,
        )
        addComponent(
            component = progressBar,
            gridx = 0,
            gridy = 1,
            gridwidth = 4,
            fill = GridBagConstraints.HORIZONTAL,
            weightx = 1.0,
        )
        addComponent(
            component = Box.createHorizontalBox(),
            gridx = 0,
            gridy = 2,
            fill = GridBagConstraints.HORIZONTAL,
            weightx = 1.0,
        )
        addComponent(
            component = startButton,
            gridx = 1,
            gridy = 2,
        )
        addComponent(
            component = Box.createHorizontalStrut(10),
            gridx = 2,
            gridy = 2,
        )
        addComponent(
            component = stopButton,
            gridx = 3,
            gridy = 2,
        )
    }

}