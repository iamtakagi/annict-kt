package jp.annict.services.me

import jp.annict.client.AnnictClient
import jp.annict.exception.AnnictError
import jp.annict.models.Me
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.serializer
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeGetRequestQuery(val fields: Array<String>? =null) {
    internal fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("me")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }

        }.build()
    }
}

@Serializable
class MeGetResponseData() : Me() {

    internal fun parse(response: Response): MeGetResponseData? {
        response.apply {
            if (response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json() { isLenient = true }.decodeFromString<MeGetResponseData>(it) }
        }
    }
}

class MeService(val client: AnnictClient) {

    internal fun get(query: MeGetRequestQuery) : MeGetResponseData? {
        this.client.apply { return MeGetResponseData()
            .parse(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}