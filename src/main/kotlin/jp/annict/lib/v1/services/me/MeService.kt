package jp.annict.lib.v1.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.lib.bases.BaseService
import jp.annict.lib.interfaces.IAnnictClient
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.models.User
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeGetRequestQuery(val fields: Array<String>? =null) : RequestQuery {
    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("me")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }

        }.build()
    }
}

data class MeGetResponseData(val user: User?) : ResponseData<MeGetResponseData> {

    constructor() : this(null)

    override fun toDataClass(response: Response): MeGetResponseData {
        return MeGetResponseData(JsonUtil.GSON.fromJson(
            JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject,
            object : TypeToken<User>() {}.type)
        )
    }
}

class MeService(client: IAnnictClient) : BaseService(client){

    fun get(query: MeGetRequestQuery) : MeGetResponseData {
        this.client.apply { return MeGetResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}