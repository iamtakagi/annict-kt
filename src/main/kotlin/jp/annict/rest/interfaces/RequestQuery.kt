package jp.annict.rest.interfaces

import okhttp3.HttpUrl

/**
 * リクエスト用 クエリパラメータ生成 インターフェース
 */
interface RequestQuery {
    fun url(builder: HttpUrl.Builder): HttpUrl
}
