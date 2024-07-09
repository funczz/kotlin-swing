import demo.painter.BgImagePainterPanel
import demo.start
import java.awt.Dimension
import javax.swing.JFrame

internal class BgImagePainterDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(BgImagePainterPanel().also {
                it.preferredSize = Dimension(1024, 768)
            })
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}