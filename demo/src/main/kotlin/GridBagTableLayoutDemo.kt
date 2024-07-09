import demo.layout.GridBagTableLayoutPanel
import demo.start
import javax.swing.JFrame

internal class GridBagTableLayoutDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(GridBagTableLayoutPanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}