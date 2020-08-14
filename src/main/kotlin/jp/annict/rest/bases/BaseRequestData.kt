package jp.annict.rest.bases

import jp.annict.rest.interfaces.AnnictClient
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody

/**
 * GET以外のリクエストに利用できる
 */
abstract class BaseRequestData(val method: String) {

    abstract fun toRequestBody() : RequestBody

    abstract fun url(client: AnnictClient): HttpUrl

    fun toRequestBuilder(client: AnnictClient) : Request.Builder {
        return Request.Builder().url(url(client)).method(method, toRequestBody())
    }
}