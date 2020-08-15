package jp.annict.rest.v1.services

import com.google.gson.reflect.TypeToken
import jp.annict.rest.bases.BaseService
import jp.annict.rest.interfaces.AnnictClient
import jp.annict.rest.interfaces.RequestQuery
import jp.annict.rest.interfaces.ResponseData
import jp.annict.rest.utils.JsonUtil
import jp.annict.rest.v1.enums.Order
import jp.annict.rest.v1.models.Cast
import jp.annict.rest.v1.models.Character
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class CharactersRequestQuery(val fields           : Array<String>?,
                             val filter_ids       : Array<Long>?,
                             val filter_work_id   : Long?,
                             val page             : Long?,
                             val per_page         : Long?,
                             val sort_id          : Order?) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("characters")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_work_id != null) { addQueryParameter("filter_work_id", filter_work_id.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.name) }

        }.build()
    }
}

data class CharactersResponseData(val characters: Array<Character>?,
                                  val total_count: Long?,
                                  val next_page: Long?,
                                  val prev_page: Long?) : ResponseData<CharactersResponseData> {

    constructor() : this(null, null, null, null)

    override fun toDataClass(response: Response): CharactersResponseData {
        response.apply { JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply { return CharactersResponseData (
            JsonUtil.GSON.fromJson(getAsJsonArray("characters"), object : TypeToken<Array<Character>>() {}.type),
            if (get("total_count").isJsonNull) null else get("total_count").asLong,
            if (get("next_page").isJsonNull) null else get("next_page").asLong,
            if (get("prev_page").isJsonNull) null else get("prev_page").asLong
        ) }
        }
    }
}

class CharactersService(client: AnnictClient) : BaseService(client) {

    fun get(query: CharactersRequestQuery) : CharactersResponseData {
        this.client.apply {
            val a = request(Request.Builder().url(query.url(getUrlBuilder())))
            println(a.body?.string())
            return CharactersResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder()))))
        }
    }

}