package demo

import javax.swing.UIManager

@Suppress("Unused")
object UIManagerUtil {
    fun printDefaults() {
        UIManager.getDefaults().forEach { (k, v) ->
            println("UIManager.getDefaults: $k=$v")
        }
    }

}