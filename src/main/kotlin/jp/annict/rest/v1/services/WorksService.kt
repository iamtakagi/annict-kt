package jp.annict.rest.v1.services

import com.google.gson.reflect.TypeToken
import jp.annict.rest.interfaces.RequestQuery
import jp.annict.rest.interfaces.ResponseBody
import jp.annict.rest.utils.JsonUtil
import jp.annict.rest.v1.AnnictClient
import jp.annict.rest.v1.type.Order
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response
import java.util.*

data class Work (
    val id                : Int,
    val title             : String,
    val title_kana        : String,
    val media             : String,
    val media_text        : String,
    val season_name       : String,
    val season_name_text  : String,
    val released_on       : Date,
    val released_on_about : Date,
    val official_site_url : String,
    val wikipedia_url     : String,
    val twitter_username  : String,
    val twitter_hashtag   : String,
    val episodes_count    : Int,
    val watchers_count    : Int
)

data class WorksRequestQuery (
    val fields           : Array<String>?,
    val filter_ids       : Array<Int>?,
    val filter_season       : Array<String>?,
    val filter_title        : Array<String>?,
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
            if(filter_season != null) { addQueryParameter("filter_season", filter_season.toString()) }
            if(filter_title != null) { addQueryParameter("filter_title", filter_title.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.value) }
            if(sort_season != null) { addQueryParameter("sort_season", sort_season.value) }
            if(sort_watchers_count != null) { addQueryParameter("sort_watchers_count", sort_watchers_count.value) }

        }.build()
    }
}

data class WorksResponse (
    val works     : Array<Work>?
) : ResponseBody<RecordsResponse> {

    constructor() : this(null)

    override fun toDataClass(response: Response): RecordsResponse {
        response.apply {
            JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply {
                return RecordsResponse(
                    JsonUtil.GSON.fromJson(getAsJsonArray("works"), object : TypeToken<Array<Work>>() {}.type)
                )
            }
        }
    }
}

class WorksService(val client: AnnictClient) {

    fun get(query: RecordsRequestQuery) : RecordsResponse {
        this.client.apply {
            return RecordsResponse().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder()))))
        }
    }
}
