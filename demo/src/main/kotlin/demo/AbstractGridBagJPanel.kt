package demo

import java.awt.Component
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JPanel

abstract class AbstractGridBagJPanel : JPanel(GridBagLayout()) {
    /**
     * GridBagLayout の JPanel に、指定の制約を付与してコンポーネントを配置する
     * @param component グリッド上に配置する Component
     * @param gridx Component を配置するグリッド上の横の座標を 0 から指定する。
     * @param gridy Component を配置するグリッド上の縦の座標を 0 から指定する。
     * @param gridheight Component が占めるグリッド枠の縦の数を指定する。デフォルトは 1
     * @param gridwidth Component が占めるグリッド枠の横の数を指定する。デフォルトは 1
     * @param fill Component をグリッド枠いっぱいに広げるかどうかを指定する。デフォルトはなし。なし（NONE）、縦（VERTICAL）、横（HORIZONTAL）、両方（BOTH）
     * @param insets Component の間隔を指定する。デフォルトはすべて 0
     * @param anchor Component をどこに寄せるかを指定する。デフォルトは中央。「CENTER」、「NORTH」、「NORTHEAST」、「EAST」、「SOUTHEAST」、「SOUTH」、「SOUTHWEST」、「WEST」、「NORTHWEST」
     * @param weightx Component の間隔を分配する横の比率を指定する。この指定は行・列単位で有効となる。デフォルトは 0.0
     * @param weighty Component の間隔を分配する縦の比率を指定する。この指定は行・列単位で有効となる。デフォルトは 0.0
     */
    fun addComponent(
        component: Component,
        gridx: Int,
        gridy: Int,
        gridheight: Int = 1,
        gridwidth: Int = 1,
        fill: Int = GridBagConstraints.NONE,
        insets: Insets = Insets(0, 0, 0, 0),
        anchor: Int = GridBagConstraints.CENTER,
        weightx: Double = 0.0,
        weighty: Double = 0.0,
    ) {
        val gridBagConstraints = GridBagConstraints().also {
            it.gridx = gridx
            it.gridy = gridy
            it.gridheight = gridheight
            it.gridwidth = gridwidth
            it.fill = fill
            it.insets = insets
            it.anchor = anchor
            it.weightx = weightx
            it.weighty = weighty
        }
        add(component, gridBagConstraints)
    }

}
