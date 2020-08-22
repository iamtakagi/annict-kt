package jp.annict.lib.v1.services

import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import jp.annict.lib.bases.BaseService
import jp.annict.lib.bases.BaseRequestData
import jp.annict.lib.interfaces.AnnictClient
import jp.annict.lib.interfaces.FormBodyData
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.models.Application
import okhttp3.*
import java.text.Normalizer

data class TokenGetFormBodyData (
    val client_id: String?,
    val client_secret: String?,
    val grant_type: String?,
    val redirect_uri: String?,
    val code: String?
) : FormBodyData {

    override fun toFormBody(): RequestBody {
        return FormBody.Builder().apply {
            if(client_id != null && client_id.isNotEmpty()) { addEncoded("client_id", client_id )}
            if(client_secret != null && client_secret.isNotEmpty()) { addEncoded("client_secret", client_secret )}
            if(grant_type != null && grant_type.isNotEmpty()) { addEncoded("grant_type", grant_type )}
            if(redirect_uri != null && redirect_uri.isNotEmpty()) { addEncoded("redirect_uri", redirect_uri )}
            if(code != null && code.isNotEmpty()) { addEncoded("code", code )}

        }.build()
    }

}

data class TokenGetResponseData (
    val access_token: String?,
    val token_Type: String?,
    val scope: String?,
    val created_at: Long?
) : ResponseData<TokenGetResponseData> {

    constructor() : this(null, null, null, null)

    override fun toDataClass(response: Response): TokenGetResponseData {
        response.apply {
            return JsonUtil.GSON.fromJson(
                JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject,
                object : TypeToken<TokenGetResponseData>() {}.type
            )
        }
    }
}

data class RevokeTokenPostRequestData (
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

data class TokenInfoGetResponseData (
    val resource_owner_id: Long?,
    val scopes: Array<String>?,
    val expires_in_seconds: String?,
    val application: Application?,
    val created_at: Long?
) : ResponseData<TokenInfoGetResponseData> {

    constructor() : this(null, null, null, null, null)

    override fun toDataClass(response: Response): TokenInfoGetResponseData {
        response.apply {
            return JsonUtil.GSON.fromJson(
                JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject,
                object : TypeToken<TokenInfoGetResponseData>() {}.type
            )
        }
    }
}

class AuthorizationService(client: AnnictClient) : BaseService(client) {

    /**
     * アクセストークンを取得する
     */
        fun postToken(data: TokenGetFormBodyData): TokenGetResponseData {
        this.client.apply {
            println(request(Request.Builder().url(getUrlBuilder().addPathSegments("oauth/token").build()).method("post", data.toFormBody())))

            return TokenGetResponseData().toDataClass(request(Request.Builder().url(getUrlBuilder().addPathSegments("oauth/token").build()).method("post", data.toFormBody()))) }
    }

    /**
     * 認証ユーザの情報を取得する
     */
    fun getInfo(): TokenInfoGetResponseData {
        this.client.apply {
            return TokenInfoGetResponseData().toDataClass(
                request(
                    Request.Builder().url(getUrlBuilder().addPathSegments("/oauth/token/info").build())
                )
            )
        }
    }

    /**
     * トークンを失効させる
     */
    fun postRevoke(data: RevokeTokenPostRequestData): Boolean {
        this.client.apply {
            return (request(data.toRequestBuilder(client)).code == 200)
        }
    }
}
