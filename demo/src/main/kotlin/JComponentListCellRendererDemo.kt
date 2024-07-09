import demo.list.JComponentListCellRendererPanel
import demo.start
import javax.swing.JFrame

internal class JComponentListCellRendererDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            //JFrame().start(ElementPanel(0))
            JFrame().start(JComponentListCellRendererPanel(selectionBackground = null))
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}