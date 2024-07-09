import demo.icon.CircleAnimatedIconLabelPanel
import demo.start
import java.awt.Color
import javax.swing.JFrame

class CircleAnimatedIconLabelDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(CircleAnimatedIconLabelPanel(ellipseColor = Color.BLUE, iconSize = 6, delay = 500))
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}