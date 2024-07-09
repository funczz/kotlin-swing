import demo.FunctionalDocumentListenerPanel
import demo.start
import javax.swing.JFrame

class FunctionalDocumentListenerDemo {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            JFrame().start(FunctionalDocumentListenerPanel())
            while (true) {
                Thread.sleep(1L)
            }
        }

    }

}