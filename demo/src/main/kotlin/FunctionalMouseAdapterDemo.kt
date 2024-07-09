import demo.FunctionalMouseAdapterPanel
import demo.start
import javax.swing.JFrame

class FunctionalMouseAdapterDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(FunctionalMouseAdapterPanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}