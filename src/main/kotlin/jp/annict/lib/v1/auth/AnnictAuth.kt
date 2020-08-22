package jp.annict.lib.v1.auth

import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import jp.annict.lib.interfaces.RequestBodyData
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.models.Application
import okhttp3.*

data class TokenGetRequestData (
    val client_id: String? =null,
    val client_secret: String? =null,
    val grant_type: String? ="authorization_code",
    val redirect_uri: String? ="urn:ietf:wg:oauth:2.0:oob",
    val code: String? =null,
) : RequestBodyData {

    override fun toRequestBody() : RequestBody {

       return MultipartBody.Builder().apply {
            if (client_id != null && client_id.isNotEmpty()) { addFormDataPart("client_id", client_id) }
            if (client_secret != null && client_secret.isNotEmpty()) { addFormDataPart("client_secret", client_secret) }
            if (grant_type != null && grant_type.isNotEmpty()) { addFormDataPart("grant_type", grant_type) }
            if (redirect_uri != null && redirect_uri.isNotEmpty()) { addFormDataPart("redirect_uri", redirect_uri) }
            if (code != null && code.isNotEmpty()) { addFormDataPart("code", code) }

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
    val client_id: String? =null,
    val client_secret: String? =null,
    val token: String? =null
) : RequestBodyData {

    override fun toRequestBody() : RequestBody {

        return MultipartBody.Builder().apply {
            if(client_id != null && client_id.isNotEmpty()) { addFormDataPart("client_id", client_id )}
            if(client_secret != null && client_secret.isNotEmpty()) { addFormDataPart("client_secret", client_secret )}
            if(token != null && token.isNotEmpty()) { addFormDataPart("token", token )}
        }.build()
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


class AnnictAuth {

    val client = OkHttpClient()

    private fun getUrlBuilder(): HttpUrl.Builder = HttpUrl.Builder().scheme("https").host("api.annict.com")

    /**
     * アクセストークンを取得する
     */
    fun token(data: TokenGetRequestData): TokenGetResponseData {
        val res =  this.client.newCall(
            Request.Builder().url(getUrlBuilder().addPathSegments("oauth/token").build())
                .post(data.toRequestBody()).build()).execute()

        println(res)

        return TokenGetResponseData().toDataClass(res)
    }

    /**
     * 認証ユーザの情報を取得する
     */
    fun info(token: String): TokenInfoGetResponseData {
        val res =  this.client.newCall(
            Request.Builder().header("Authorization", "Bearer $token").url(getUrlBuilder().addPathSegments("oauth/token/info").build()).build()
        ).execute()

        println(res)

        return TokenInfoGetResponseData().toDataClass(res)
    }

    /**
     * トークンを失効させる
     */
    fun revoke(data: RevokeTokenPostRequestData): Boolean {
       return this.client.newCall(Request.Builder().url(getUrlBuilder().addPathSegments("oauth/revoke").build()).post(data.toRequestBody()).build()).execute().code == 200
    }

}
