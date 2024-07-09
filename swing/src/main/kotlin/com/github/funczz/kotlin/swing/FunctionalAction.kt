package com.github.funczz.kotlin.swing

import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.Action
import javax.swing.Icon
import javax.swing.ImageIcon

/**
 * イベントに対して実行する関数を任意にセットできるアクションクラス
 * @author funczz
 */
@Suppress("Unused")
open class FunctionalAction(

    /**
     * アクション名
     */
    name: String? = null,

    /**
     * アイコン
     */
    icon: Icon? = null,

    /**
     * イベントで実行する関数
     */
    private val f: (ActionEvent) -> Unit

) : AbstractAction(name, icon) {

    override fun actionPerformed(p0: ActionEvent) {
        f(p0)
    }

    /**
     * アクション名を取得する。
     * @return
     */
    open fun getActionName(): String {
        return getValue(Action.NAME)?.let { it as String } ?: ""
    }

    /**
     * アクション名をセットする。
     * @param value 名前
     */
    open fun setActionName(value: String) {
        putValue(Action.NAME, value)
    }

    /**
     * 詳細な説明を取得する。
     * @return
     */
    open fun getLongDescription(): String {
        return getValue(Action.LONG_DESCRIPTION)?.let { it as String } ?: ""
    }

    /**
     * 詳細な説明をセットする。
     * @param value 詳細な説明
     */
    open fun setLongDescription(value: String) {
        putValue(Action.LONG_DESCRIPTION, value)
    }

    /**
     * 簡易な説明を取得する。
     * @return
     */
    open fun getShortDescription(): String {
        return getValue(Action.SHORT_DESCRIPTION)?.let { it as String } ?: ""
    }

    /**
     * 簡易な説明をセットする。
     * @param value 簡易な説明
     */
    open fun setShortDescription(value: String) {
        putValue(Action.SHORT_DESCRIPTION, value)
    }

    /**
     * アイコンを取得する。
     * @return
     */
    open fun getIcon(): ImageIcon? {
        return getValue(Action.SMALL_ICON)?.let { it as ImageIcon }
    }

    /**
     * アイコンをセットする。
     * @param value アイコン
     */
    open fun setIcon(value: ImageIcon) {
        putValue(Action.SMALL_ICON, value)
    }

}