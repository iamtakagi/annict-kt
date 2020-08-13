package jp.annict.rest.v1.services

import com.google.gson.JsonObject
import jp.annict.rest.utils.JsonUtil
import jp.annict.rest.v1.AnnictClient
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

data class TokenRequestBody (
    val client_id: String,
    val client_secret: String,
    val grant_type: String,
    val redirect_uri: String,
    val code: String
)

data class RevokeTokenRequestBody (
    val client_id: String,
    val client_secret: String,
    val token: String
)

class AuthorizationService(val client: AnnictClient) {


    /**
     * アクセストークンを取得する
     */
    fun token(body: TokenRequestBody) : Response {
        this.client.apply {
            return request(Request.Builder().post(RequestBody.create("application/json; charset=utf-8".toMediaType(), JsonUtil.GSON.toJson(body))).url(getUrlBuilder().addPathSegments("/oauth/token").build()))
        }
    }

    /**
     * 認証ユーザの情報を取得する
     */
    fun info(): Response {
        this.client.apply {
            return request(Request.Builder().url(getUrlBuilder().addPathSegments("/oauth/token/info").build()))
        }
    }

    /**
     * トークンを失効させる
     */
    fun revoke(body: RevokeTokenRequestBody) : Response {
        this.client.apply {
            return request(Request.Builder().post(RequestBody.create("application/json; charset=utf-8".toMediaType(), JsonUtil.GSON.toJson(body))).url(getUrlBuilder().addPathSegments("oauth/revoke").build()))
        }
    }
}