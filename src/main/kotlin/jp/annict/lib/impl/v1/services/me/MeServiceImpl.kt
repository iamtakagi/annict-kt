package jp.annict.lib.impl.v1.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.lib.interfaces.IAnnictClient
import jp.annict.lib.interfaces.services.me.MeGetRequestQuery
import jp.annict.lib.interfaces.services.me.MeGetResponseData
import jp.annict.lib.interfaces.services.me.MeService
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.impl.v1.models.User
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeGetRequestQueryImpl(val fields: Array<String>? =null) : MeGetRequestQuery {
    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("me")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }

        }.build()
    }
}

data class MeGetResponseDataImpl(val user: User?) : MeGetResponseData {

    constructor() : this(null)

    override fun toDataClass(response: Response): MeGetResponseData {
        return MeGetResponseDataImpl(JsonUtil.GSON.fromJson(
            JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject,
            object : TypeToken<User>() {}.type)
        )
    }
}

class MeServiceImpl(val client: IAnnictClient) : MeService {

    override fun get(query: MeGetRequestQuery) : MeGetResponseData {
        this.client.apply { return MeGetResponseDataImpl().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}