import demo.icon.CharAnimatedIconLabelPanel
import demo.start
import java.awt.Color
import javax.swing.JFrame

class CharAnimatedIconLabelDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(CharAnimatedIconLabelPanel(char = '>', repeat = 5, color = Color.BLUE, delay = 500))
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}