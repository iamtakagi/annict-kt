package jp.annict.rest.v1.services

import jp.annict.rest.bases.BaseAnnictService
import jp.annict.rest.bases.BaseRequestData
import jp.annict.rest.interfaces.AnnictClient
import jp.annict.rest.utils.JsonUtil
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType

data class TokenBaseRequestData (
    val client_id: String,
    val client_secret: String,
    val grant_type: String,
    val redirect_uri: String,
    val code: String
) : BaseRequestData("post") {
    override fun toRequestBody(): RequestBody {
        return RequestBody.create("application/json; charset=utf-8".toMediaType(), JsonUtil.GSON.toJson(this))
    }

    override fun url(client: AnnictClient): HttpUrl {
        return client.getUrlBuilder().addPathSegments("oauth/token").build()
    }
}

data class RevokeTokenBaseRequestData (
    val client_id: String,
    val client_secret: String,
    val token: String
) : BaseRequestData("post") {
    override fun toRequestBody(): RequestBody {
        return RequestBody.create("application/json; charset=utf-8".toMediaType(), JsonUtil.GSON.toJson(this))
    }

    override fun url(client: AnnictClient): HttpUrl {
        return client.getUrlBuilder().addPathSegments("oauth/revoke").build()
    }
}

class AuthorizationAnnictService(client: AnnictClient) : BaseAnnictService(client) {

    /**
     * アクセストークンを取得する
     */
    fun token(data: TokenBaseRequestData) : Response {
        return client.request(data.toRequestBuilder(client))
    }

    /**
     * 認証ユーザの情報を取得する
     */
    fun info(): Response {
        return client.request(Request.Builder().url(client.getUrlBuilder().addPathSegments("oauth/token/info").build()))
    }

    /**
     * トークンを失効させる
     */
    fun revoke(data: RevokeTokenBaseRequestData) : Response {
        return client.request(data.toRequestBuilder(client))
    }
}