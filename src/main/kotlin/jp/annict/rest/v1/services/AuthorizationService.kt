package jp.annict.rest.v1.services

import com.google.gson.reflect.TypeToken
import jp.annict.rest.bases.BaseAnnictService
import jp.annict.rest.bases.BaseRequestData
import jp.annict.rest.interfaces.AnnictClient
import jp.annict.rest.interfaces.ResponseData
import jp.annict.rest.utils.JsonUtil
import jp.annict.rest.v1.models.Application
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType

data class TokenRequestData (
    val client_id: String?,
    val client_secret: String?,
    val grant_type: String?,
    val redirect_uri: String?,
    val code: String?
) : BaseRequestData("post") {
    override fun toRequestBody(): RequestBody {
        return RequestBody.create("application/json; charset=utf-8".toMediaType(), JsonUtil.GSON.toJson(this))
    }

    override fun url(client: AnnictClient): HttpUrl {
        return client.getUrlBuilder().addPathSegments("oauth/token").build()
    }
}

data class TokenResponseData (
    val access_token: String?,
    val token_Type: String?,
    val scope: String?,
    val created_at: Long?
) : ResponseData<TokenResponseData> {

    constructor() : this(null, null, null, null)

    override fun toDataClass(response: Response): TokenResponseData {
        response.apply {
            return JsonUtil.GSON.fromJson(JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject, object : TypeToken<TokenResponseData>() {}.type)
        }
    }
}

data class RevokeTokenRequestData (
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

data class TokenInfoResponseData (
    val resource_owner_id: Int?,
    val scopes: Array<String>?,
    val expires_in_seconds: String?,
    val application: Application?,
    val created_at: Long?
) : ResponseData<TokenInfoResponseData> {

    constructor() : this(null, null, null, null, null)

    override fun toDataClass(response: Response): TokenInfoResponseData {
        response.apply {
            return JsonUtil.GSON.fromJson(JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject, object : TypeToken<TokenInfoResponseData>() {}.type)
        }
    }
}

class AuthorizationAnnictService(client: AnnictClient) : BaseAnnictService(client) {

    /**
     * アクセストークンを取得する
     */
    fun token(data: TokenRequestData) : TokenResponseData {
        this.client.apply {
            return TokenResponseData().toDataClass(request(Request.Builder().url(data.url(this))))
        }
    }

    /**
     * 認証ユーザの情報を取得する
     */
    fun info(): TokenInfoResponseData {
        this.client.apply {
            return TokenInfoResponseData().toDataClass(request(Request.Builder().url(getUrlBuilder().addEncodedPathSegments("oauth/token/info").build())))
        }
    }

    /**
     * トークンを失効させる
     */
    fun revoke(data: RevokeTokenRequestData) : Response {
         this.client.apply {
            return request(Request.Builder().url(data.url(this)))
        }
    }
}