import demo.start
import demo.table.AbstractFutureReadableTableModelPanel
import javax.swing.JFrame

internal class AbstractFutureReadableTableModelDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(AbstractFutureReadableTableModelPanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}