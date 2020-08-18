package jp.annict.lib.v1.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.lib.bases.BaseService
import jp.annict.lib.interfaces.AnnictClient
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.models.User
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeRequestQuery(val fields: Array<String>?) : RequestQuery {
    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("me")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }

        }.build()
    }
}

data class MeResponseData(val user: User?) : ResponseData<MeResponseData> {

    constructor() : this(null)

    override fun toDataClass(response: Response): MeResponseData {
        return MeResponseData(JsonUtil.GSON.fromJson(
            JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject,
            object : TypeToken<User>() {}.type)
        )
    }
}

class MeService(client: AnnictClient) : BaseService(client){

    fun get(query: MeRequestQuery) : MeResponseData {
        this.client.apply { return MeResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}