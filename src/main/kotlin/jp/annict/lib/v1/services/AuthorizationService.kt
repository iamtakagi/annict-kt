package jp.annict.lib.v1.services

import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import jp.annict.lib.bases.BaseService
import jp.annict.lib.bases.BaseRequestData
import jp.annict.lib.interfaces.AnnictClient
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.models.Application
import jp.annict.lib.v1.services.me.MeResponseData
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType

data class TokenRequestQuery (
    val client_id: String?,
    val client_secret: String?,
    val grant_type: String?,
    val redirect_uri: String?,
    val code: String?
) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("/oauth/token")

            if(client_id != null && client_id.isNotEmpty()) { addQueryParameter("client_id", client_id )}
            if(client_secret != null && client_secret.isNotEmpty()) { addQueryParameter("client_secret", client_secret )}
            if(grant_type != null && grant_type.isNotEmpty()) { addQueryParameter("grant_type", grant_type )}
            if(redirect_uri != null && redirect_uri.isNotEmpty()) { addQueryParameter("redirect_uri", redirect_uri )}
            if(code != null && code.isNotEmpty()) { addQueryParameter("code", code )}

        }.build()
    }

}

data class TokenResponseData(
    val access_token: String?,
    val token_Type: String?,
    val scope: String?,
    val created_at: Long?
) : ResponseData<TokenResponseData> {

    constructor() : this(null, null, null, null)

    override fun toDataClass(response: Response): TokenResponseData {
        response.apply {
            return JsonUtil.GSON.fromJson(
                JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject,
                object : TypeToken<TokenResponseData>() {}.type
            )
        }
    }
}

data class RevokeTokenRequestData(
    val client_id: String,
    val client_secret: String,
    val token: String
) : BaseRequestData("post", "application/json; charset=utf-8", "oauth/revoke") {

    override fun toJsonObject(): JsonObject {
        return JsonObject().apply {
            addProperty("client_id", client_id)
            addProperty("client_secret", client_id)
            addProperty("token", token)
        }
    }
}

data class TokenInfoResponseData(
    val resource_owner_id: Long?,
    val scopes: Array<String>?,
    val expires_in_seconds: String?,
    val application: Application?,
    val created_at: Long?
) : ResponseData<TokenInfoResponseData> {

    constructor() : this(null, null, null, null, null)

    override fun toDataClass(response: Response): TokenInfoResponseData {
        response.apply {
            return JsonUtil.GSON.fromJson(
                JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject,
                object : TypeToken<TokenInfoResponseData>() {}.type
            )
        }
    }
}

class AuthorizationService(client: AnnictClient) : BaseService(client) {

    /**
     * アクセストークンを取得する
     */
    fun token(query: TokenRequestQuery): TokenResponseData {
        this.client.apply {
            println(request(Request.Builder().url(query.url(getUrlBuilder())).method("post", null)).body?.string())
            return TokenResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())).method("post", null))) }
    }

    /**
     * 認証ユーザの情報を取得する
     */
    fun info(): TokenInfoResponseData {
        this.client.apply {
            return TokenInfoResponseData().toDataClass(
                request(
                    Request.Builder().url(getUrlBuilder().addEncodedPathSegments("/oauth/token/info").build())
                )
            )
        }
    }

    /**
     * トークンを失効させる
     */
    fun revoke(data: RevokeTokenRequestData): Response {
        this.client.apply {
            return request(Request.Builder().url(data.url(this)))
        }
    }
}
