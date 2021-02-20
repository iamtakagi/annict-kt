package com.github.iamtakagi.kannict.auth

import jp.annict.exception.AnnictError
import jp.annict.models.Application
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*

/**
 * Token get request data
 *
 * @property client_id
 * @property client_secret
 * @property grant_type
 * @property redirect_uri
 * @property code
 * @constructor Create empty Token get request data
 */
data class TokenGetRequestData (
    val client_id: String? =null,
    val client_secret: String? =null,
    val grant_type: String? ="authorization_code",
    val redirect_uri: String? ="urn:ietf:wg:oauth:2.0:oob",
    val code: String? =null,
) {

    internal fun toRequestBody() : RequestBody {

        return MultipartBody.Builder().apply {
            if (client_id != null && client_id.isNotEmpty()) { addFormDataPart("client_id", client_id) }
            if (client_secret != null && client_secret.isNotEmpty()) { addFormDataPart("client_secret", client_secret) }
            if (grant_type != null && grant_type.isNotEmpty()) { addFormDataPart("grant_type", grant_type) }
            if (redirect_uri != null && redirect_uri.isNotEmpty()) { addFormDataPart("redirect_uri", redirect_uri) }
            if (code != null && code.isNotEmpty()) { addFormDataPart("code", code) }

        }.build()

    }
}

/**
 * Token get response data
 *
 * @property access_token
 * @property token_type
 * @property scope
 * @property created_at
 * @constructor Create empty Token get response data
 */
@Serializable
data class TokenGetResponseData (
    val access_token: String? = null,
    val token_type: String? = null,
    val scope: String? = null,
    val created_at: Long? = null
) {

    constructor() : this(null, null, null, null)

    internal fun parse(response: Response): com.github.iamtakagi.kannict.auth.TokenGetResponseData? {
        response.apply {
            if (response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json { isLenient = true }.decodeFromString<com.github.iamtakagi.kannict.auth.TokenGetResponseData>(it) }
        }
    }
}

/**
 * Token info get response data
 *
 * @property resource_owner_id
 * @property scope
 * @property expires_in
 * @property application
 * @property created_at
 * @constructor Create empty Token info get response data
 */
@Serializable
data class TokenInfoGetResponseData(
    val resource_owner_id: Long? = null,
    val scope: Array<String>? = null,
    val expires_in: String? = null,
    val application: Application? = null,
    val created_at: Long? = null
) {

    constructor() : this(null, null, null, null, null)

    internal fun parse(response: Response): com.github.iamtakagi.kannict.auth.TokenInfoGetResponseData? {
        response.apply {
            if (response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json { isLenient = true }.decodeFromString<com.github.iamtakagi.kannict.auth.TokenInfoGetResponseData>(it) }
        }
    }
}

/**
 * Revoke token post request data
 *
 * @property client_id
 * @property client_secret
 * @property token
 * @constructor Create empty Revoke token post request data
 */
data class RevokeTokenPostRequestData(
    val client_id: String? = null,
    val client_secret: String? = null,
    val token: String? = null
) {

    internal fun toRequestBody(): RequestBody {

        return MultipartBody.Builder().apply {
            if (client_id != null && client_id.isNotEmpty()) {
                addFormDataPart("client_id", client_id)
            }
            if (client_secret != null && client_secret.isNotEmpty()) {
                addFormDataPart("client_secret", client_secret)
            }
            if (token != null && token.isNotEmpty()) {
                addFormDataPart("token", token)
            }

        }.build()
    }
}

/**
 * Annict auth
 *
 * @constructor Create empty Annict auth
 */
class AnnictAuth {

    val client = OkHttpClient()

    internal fun getUrlBuilder(): HttpUrl.Builder = HttpUrl.Builder().scheme("https").host("api.annict.com")

    /**
     * アクセストークンを取得する
     */
    fun token(
        client_id: String? = null,
        client_secret: String? = null,
        grant_type: String? = "authorization_code",
        redirect_uri: String? = "urn:ietf:wg:oauth:2.0:oob",
        code: String? = null
    ): com.github.iamtakagi.kannict.auth.TokenGetResponseData? = com.github.iamtakagi.kannict.auth.TokenGetResponseData()
        .parse(
            this.client.newCall(
                Request.Builder().url(getUrlBuilder().addPathSegments("oauth/token").build()).post(
                    com.github.iamtakagi.kannict.auth.TokenGetRequestData(
                        client_id,
                        client_secret,
                        grant_type,
                        redirect_uri,
                        code
                    ).toRequestBody()
                ).build()
            ).execute()
        )


    /**
     * 認証ユーザの情報を取得する
     */
    fun info(token: String): com.github.iamtakagi.kannict.auth.TokenInfoGetResponseData? {

        this.client.newCall(
            Request.Builder().header("Authorization", "Bearer $token").url(getUrlBuilder().addPathSegments("oauth/token/info").build()).build()
        ).execute().apply {
            if (code != 200) {
                return throw AnnictError(message)
            }
            return com.github.iamtakagi.kannict.auth.TokenInfoGetResponseData().parse(this)
        }
    }

    /**
     * トークンを失効させる
     */
    fun revoke(client_id: String? = null, client_secret: String? = null, token: String? = null): Boolean? =
        this.client.newCall(
            Request.Builder().url(getUrlBuilder().addPathSegments("oauth/revoke").build()).post(
                com.github.iamtakagi.kannict.auth.RevokeTokenPostRequestData(client_id, client_secret, token).toRequestBody()
            ).build()
        ).execute().code == 200
}


