package jp.annict.rest.v1.services

import jp.annict.rest.v1.AnnictClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

data class AccessToken(
    val access_token: String,
    val token_type: String,
    val scope: String,
    val created_at: String
)

data class TokenRequest(
    val client_id: String,
    val client_secret: String,
    val grant_type: String,
    val redirect_uri: String,
    val code: String
)

class AuthorizationService(val client: AnnictClient) {

    /**
     * 認証ユーザの情報を取得する
     */
    fun info(): Response {
        this.client.apply {
            return request(Request.Builder().url(getUrlBuilder().addPathSegments("/oauth/token/info").build()))
        }
    }
}