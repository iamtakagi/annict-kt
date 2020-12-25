package jp.annict.services

import com.google.gson.reflect.TypeToken
import jp.annict.client.AnnictClient
import jp.annict.utils.JsonUtil
import jp.annict.enums.Order
import jp.annict.models.Series
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class SeriesGetRequestQuery(
    val fields           : Array<String>?=null,
    val filter_ids       : Array<Long>?=null,
    val filter_name   : String?=null,
    val page             : Long?=null,
    val per_page         : Long?=null,
    val sort_id          : Order?=null) {

     fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("series")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_name != null) { addQueryParameter("filter_name", filter_name.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.toString()) }

        }.build()
    }
}

data class SeriesGetResponseData (
    val organizations: Array<Series>?,
    val total_count: Long?,
    val next_page: Long?,
    val prev_page: Long?
) {

    constructor() : this(null, null, null, null)

     fun toDataClass(response: Response): SeriesGetResponseData {
        response.apply { JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply { return SeriesGetResponseData(
            JsonUtil.GSON.fromJson(
                getAsJsonArray("organizations"),
                object : TypeToken<Array<Series>>() {}.type
            ),
            if (get("total_count").isJsonNull) null else get("total_count").asLong,
            if (get("next_page").isJsonNull) null else get("next_page").asLong,
            if (get("prev_page").isJsonNull) null else get("prev_page").asLong
        )
        }
        }
    }
}

class SeriesService(val client: AnnictClient) {

    fun get(query: SeriesGetRequestQuery) : SeriesGetResponseData {
        this.client.apply {
            return SeriesGetResponseData()
                .toDataClass(request(Request.Builder().url(query.url(getUrlBuilder()))))
        }
    }

}