package jp.annict.rest.interfaces

import okhttp3.HttpUrl

/**
 * GETリクエスト用 クエリパラメータ生成インターフェース
 */
interface RequestQuery {
    fun url(builder: HttpUrl.Builder): HttpUrl
}
