import demo.start
import demo.table.JTableHeaderWithoutJScrollPanePanel
import javax.swing.JFrame

internal class JTableHeaderWithoutJScrollPaneDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(JTableHeaderWithoutJScrollPanePanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}