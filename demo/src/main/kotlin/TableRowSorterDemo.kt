import demo.start
import demo.table.TableRowSorterPanel
import javax.swing.JFrame

internal class TableRowSorterDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(TableRowSorterPanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}