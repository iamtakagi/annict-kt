package jp.annict.rest.v1.services

import com.google.gson.JsonArray
import jp.annict.rest.interfaces.RequestQuery
import jp.annict.rest.v1.AnnictClient
import jp.annict.rest.interfaces.ResponseData
import jp.annict.rest.utils.JsonUtil
import jp.annict.rest.v1.type.Order
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class Episode (
    val id: Int,
    val number: Int,
    val number_text   : String,
    val sort_number   : Int,
    val title: String,
    val records_count : Int,
    val work: Work,
    val prev_episode  : Episode?,
    val next_episode  : Episode?
)

data class EpisodesRequestQuery (
    val fields           : String?,
    val filter_ids       : String?,
    val filter_work_id   : Int?,
    val page             : Int?,
    val per_page         : Int?,
    val sort_id          : Order?,
    val sort_sort_number : Order?
) : RequestQuery {

    override fun url(builer: HttpUrl.Builder) : HttpUrl {
       return builer.apply {
            addPathSegment("episodes")

            if(fields != null) {
                addQueryParameter("fields", fields)
            }
            if(filter_ids != null) {
                addQueryParameter("filter_ids", filter_ids)
            }
            if(filter_work_id != null) {
                addQueryParameter("filter_work_id", filter_work_id.toString())
            }
            if(page != null) {
                addQueryParameter("page", page.toString())
            }
            if(per_page != null) {
                addQueryParameter("per_page", per_page.toString())
            }
            if(sort_id != null) {
                addQueryParameter("sort_id", sort_id.name)
            }
            if(sort_sort_number != null) {
                addQueryParameter("sort_sort_number", sort_sort_number.name)
            }
        }.build()
    }
}

data class EpisodesResponse(
    val episodes: Array<Episode>?,
    val total_count: Int?,
    val next_page: Int?,
    val prev_page: Int?
) : ResponseData<EpisodesResponse> {

    constructor() : this(null, null,null,null)

    override fun toDataClass(response: Response): EpisodesResponse {
        response.apply {
            JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply {
                return EpisodesResponse (
                    JsonUtil.GSON.fromJson(getAsJsonArray("total_count"), Array<Episode>::class.java),
                    getAsJsonObject("total_count").asInt,
                    getAsJsonObject("next_page").asInt,
                    getAsJsonObject("prev_page").asInt
                )
            }
        }
    }
}

class EpisodesService(val client: AnnictClient) {

    fun get(query: EpisodesRequestQuery) : EpisodesResponse {
        this.client.apply {
            return EpisodesResponse().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder()))))
        }
    }
}