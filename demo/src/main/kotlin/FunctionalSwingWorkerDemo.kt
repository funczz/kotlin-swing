import demo.FunctionalSwingWorkerPanel
import demo.start
import javax.swing.JFrame

internal class FunctionalSwingWorkerDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(FunctionalSwingWorkerPanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}