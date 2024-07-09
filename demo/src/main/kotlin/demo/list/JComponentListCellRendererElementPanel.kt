package demo.list

import demo.AbstractGridBagJPanel
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import javax.swing.JLabel
import javax.swing.JTextArea

class JComponentListCellRendererElementPanel(

    number: Int

) : AbstractGridBagJPanel() {

    private val title = JLabel("$number").also {
        it.isOpaque = false //背景を透明にする
        val dimension = Dimension(50, it.preferredSize.height)
        it.preferredSize = dimension
        it.minimumSize = dimension
    }

    private val subject = JTextArea().also {
        it.isOpaque = false //背景を透明にする
        it.append("The quick brown fox jumps over the lazy dog.")
        it.append("\n")
        it.append("How razorback-jumping frogs can level six piqued gymnasts! ")
    }

    private val date = JLabel().also {
        it.isOpaque = false //背景を透明にする
        val timestamp = Timestamp.from(Instant.now()) //UTC時間を取得
        val formatted = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).also { simpleDateFormat ->
            simpleDateFormat.timeZone = TimeZone.getDefault()
        }.format(timestamp)
        it.text = formatted
    }

    init {
        addComponent(
            component = title,
            gridx = 0,
            gridy = 0,
            anchor = GridBagConstraints.WEST,
        )
        addComponent(
            component = subject,
            gridx = 0,
            gridy = 1,
            fill = GridBagConstraints.BOTH,
            weightx = 1.0,
            weighty = 1.0,
        )
        addComponent(
            component = date,
            gridx = 0,
            gridy = 2,
            anchor = GridBagConstraints.EAST,
        )
        println("Created ElementPanel. number=$number preferredSize=$preferredSize")
    }

}
