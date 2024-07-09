import demo.start
import demo.table.AlternateRowBackgroundJTablePanel
import javax.swing.JFrame

internal class AlternateRowBackgroundJTableDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(AlternateRowBackgroundJTablePanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}