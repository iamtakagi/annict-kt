package jp.annict.lib.bases

import com.google.gson.JsonObject
import jp.annict.lib.interfaces.AnnictClient
import jp.annict.lib.utils.JsonUtil
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody

/**
 * GET以外のリクエストに利用できる
 */
abstract class BaseRequestData(val method: String, val mediaType: String, val path: String) {

    abstract fun toJsonObject() : JsonObject

    fun url(client: AnnictClient): HttpUrl {
       return  client.getUrlBuilder().addPathSegments(path).build()
    }

    fun toRequestBody(): RequestBody {
        return RequestBody.create(mediaType.toMediaType(), toJsonObject().toString())
    }

    fun toRequestBuilder(client: AnnictClient) : Request.Builder {
        return Request.Builder().url(url(client)).method(method, toRequestBody())
    }
}