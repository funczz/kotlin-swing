package com.github.funczz.kotlin.swing.painter

import java.util.*

@Suppress("Unused")
object BgImagePainterFactory {

    private val PAINTERS: List<IBgImagePainter> = listOf(
        DefaultBgImagePainter,
        CenterBgImagePainter,
        TileBgImagePainter,
        FillBgImagePainter
    )

    /**
     * 名称からインスタンスを取得する。
     * @param value 名称
     * @return Optional 指定された名称に対応するインスタンス。
     */
    fun get(value: String): Optional<IBgImagePainter> {
        return PAINTERS
            .filter { it.getName() == value }
            .map { it.getInstance() }
            .firstOrNull()
            ?.let { Optional.of(it) } ?: Optional.empty()
    }

    /**
     * 名称からインスタンスを取得する。
     * @param value 名称
     * @return 指定された名称に対応するインスタンス。
     *         インスタンスが存在しない場合は DefaultBgImagePainter から取得したインスタンス。
     */
    fun getOrDefault(value: String): IBgImagePainter {
        return get(value = value)
            .orElseGet {
                DefaultBgImagePainter.getInstance()
            }
    }

}