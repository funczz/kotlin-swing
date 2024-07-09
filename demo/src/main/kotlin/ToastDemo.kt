import demo.dialog.ToastPanel
import demo.start
import javax.swing.JFrame

internal class ToastDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(ToastPanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}