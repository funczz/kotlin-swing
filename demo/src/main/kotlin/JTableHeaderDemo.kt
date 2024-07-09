import demo.start
import demo.table.JTableHeaderPanel
import javax.swing.JFrame

internal class JTableHeaderDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(JTableHeaderPanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}