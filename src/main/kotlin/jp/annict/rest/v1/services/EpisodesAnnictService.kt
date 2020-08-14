package jp.annict.rest.v1.services

import com.google.gson.reflect.TypeToken
import jp.annict.rest.bases.BaseAnnictService
import jp.annict.rest.interfaces.AnnictClient
import jp.annict.rest.interfaces.RequestQuery
import jp.annict.rest.interfaces.ResponseData
import jp.annict.rest.v1.models.Episode
import jp.annict.rest.utils.JsonUtil
import jp.annict.rest.v1.enums.Order
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class EpisodesRequestQuery (
    val fields           : Array<String>?,
    val filter_ids       : Array<Int>?,
    val filter_work_id   : Int?,
    val page             : Int?,
    val per_page         : Int?,
    val sort_id          : Order?,
    val sort_sort_number : Order?
) : RequestQuery {

    override fun url(builder: HttpUrl.Builder) : HttpUrl {
       return builder.apply {
            addPathSegment("episodes")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_work_id != null) { addQueryParameter("filter_work_id", filter_work_id.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.name) }
            if(sort_sort_number != null) { addQueryParameter("sort_sort_number", sort_sort_number.name) }


        }.build()
    }
}

data class EpisodesResponse(
    val episodes: Array<Episode>?
) : ResponseData<EpisodesResponse> {

    constructor() : this(null)

    override fun toDataClass(response: Response): EpisodesResponse {
        response.apply { JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply { return EpisodesResponse (JsonUtil.GSON.fromJson(getAsJsonArray("episodes"), object : TypeToken<Array<Episode>>() {}.type)) } }
    }
}

class EpisodesAnnictService(client: AnnictClient) : BaseAnnictService(client) {

    fun get(query: EpisodesRequestQuery) : EpisodesResponse {
        this.client.apply { return EpisodesResponse().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}