package jp.annict.rest.v1.services

import com.google.gson.reflect.TypeToken
import jp.annict.rest.bases.BaseAnnictService
import jp.annict.rest.interfaces.AnnictClient
import jp.annict.rest.interfaces.RequestQuery
import jp.annict.rest.interfaces.ResponseData
import jp.annict.rest.v1.models.Work
import jp.annict.rest.utils.JsonUtil
import jp.annict.rest.v1.enums.Order
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class WorksRequestQuery (
    val fields           : Array<String>?,
    val filter_ids       : Array<Int>?,
    val filter_season       : String?,
    val filter_title        : String?,
    val page                : Int?,
    val per_page            : Int?,
    val sort_id             : Order?,
    val sort_season         : Order?,
    val sort_watchers_count : Order?
) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("works")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_season != null && filter_season.isNotEmpty()) { addQueryParameter("filter_season", filter_season.toString()) }
            if(filter_title != null && filter_title.isNotEmpty()) { addQueryParameter("filter_title", filter_title.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.name) }
            if(sort_season != null) { addQueryParameter("sort_season", sort_season.name) }
            if(sort_watchers_count != null) { addQueryParameter("sort_watchers_count", sort_watchers_count.name) }

        }.build()
    }
}

data class WorksResponseData (
    val works: Array<Work>?
) : ResponseData<WorksResponseData> {

    constructor() : this(null)

    override fun toDataClass(response: Response): WorksResponseData {
        response.apply {
            JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply {
                return WorksResponseData(
                    JsonUtil.GSON.fromJson(getAsJsonArray("works"), object : TypeToken<Array<Work>>() {}.type)
                )
            }
        }
    }
}

class WorksAnnictService(client: AnnictClient) : BaseAnnictService(client) {

    fun get(query: WorksRequestQuery) : WorksResponseData {
        this.client.apply {
            return WorksResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder()))))
        }
    }
}
