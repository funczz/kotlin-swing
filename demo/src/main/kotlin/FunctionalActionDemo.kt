import demo.FunctionalActionPanel
import demo.start
import javax.swing.JFrame

class FunctionalActionDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(FunctionalActionPanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }
}