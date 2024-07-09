import demo.icon.TextAnimatedIconLabelPanel
import demo.start
import java.awt.Color
import javax.swing.JFrame

class TextAnimatedIconLabelDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(
                TextAnimatedIconLabelPanel(color = Color.BLUE, delay = 500)
            )
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}