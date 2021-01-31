package jp.annict.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Status
 *
 * @property locale
 * @constructor Create empty Status
 */
@Serializable
enum class Status(val locale: String) {

    @SerialName("wanna_watch") WANNA_WATCH("見たい"),
    @SerialName("watching") WATCHING("見てる"),
    @SerialName("watched") WATCHED("見た"),
    @SerialName("on_hold") ON_HOLD("一時中断"),
    @SerialName("stop_watching") STOP_WATCHING("視聴中止"),
    @SerialName("no_select") NO_SELECT("ステータスを選択");
}