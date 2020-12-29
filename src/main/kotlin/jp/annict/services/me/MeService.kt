package jp.annict.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.client.AnnictClient
import jp.annict.models.Me
import jp.annict.utils.JsonUtil
import jp.annict.models.User
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeGetRequestQuery(val fields: Array<String>? =null) {
    fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("me")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }

        }.build()
    }
}

data class MeGetResponseData(val user: User?) {

    constructor() : this(null)

    fun toDataClass(response: Response): MeGetResponseData {
        return MeGetResponseData(
            JsonUtil.GSON.fromJson(
                JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject,
                object : TypeToken<Me>() {}.type
            )
        )
    }
}

class MeService(val client: AnnictClient) {

    fun get(query: MeGetRequestQuery) : MeGetResponseData {
        this.client.apply { return MeGetResponseData()
            .toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}