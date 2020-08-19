package jp.annict.lib.v1.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.lib.bases.BaseService
import jp.annict.lib.interfaces.AnnictClient
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.enums.Order
import jp.annict.lib.v1.models.Activity
import jp.annict.lib.v1.models.Program
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

class MeProgramsRequestQuery(
    val fields: Array<String>?,
    val filter_ids: Array<Long>?,
    val filter_channel_ids: Array<Long>?,
    val filter_work_ids: Array<Long>?,
    val filter_started_at_gt: String?,
    val filter_started_at_lt: String?,
    val filter_unwatched: Boolean?,
    val filter_rebroadcast: Boolean?,
    val page: Long?,
    val per_page: Long?,
    val sort_id: Order?,
    val sort_started_at: Order?
    ) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {

            addPathSegments("/me/programs")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_channel_ids != null && filter_channel_ids.isNotEmpty()) { addQueryParameter("filter_channel_ids", filter_channel_ids.joinToString(separator = ",")) }
            if(filter_work_ids != null && filter_work_ids.isNotEmpty()) { addQueryParameter("filter_work_ids", filter_work_ids.joinToString(separator = ",")) }
            if(filter_started_at_gt != null && filter_started_at_gt.isNotEmpty()) { addQueryParameter("filter_started_at_gt", filter_started_at_gt) }
            if(filter_started_at_lt != null && filter_started_at_lt.isNotEmpty()) { addQueryParameter("filter_started_at_lt", filter_started_at_lt) }
            if(filter_unwatched != null) { addQueryParameter("filter_unwatched", filter_unwatched.toString()) }
            if(filter_rebroadcast != null) { addQueryParameter("filter_rebroadcast", filter_rebroadcast.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.name) }
            if(sort_started_at != null) { addQueryParameter("sort_started_at", sort_started_at.name) }

        }.build()
    }
}

data class MeProgramsResponseData(
    val programs: Array<Program>?,
    val total_count: Long?,
    val next_page: Long?,
    val prev_page: Long?
) : ResponseData<MeProgramsResponseData> {

    constructor() : this(null, null, null, null)

    override fun toDataClass(response: Response): MeProgramsResponseData {
        response.apply {
            JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply { return MeProgramsResponseData (
                JsonUtil.GSON.fromJson(getAsJsonArray("activities"), object : TypeToken<Array<Program>>() {}.type),
                if (get("total_count").isJsonNull) null else get("total_count").asLong,
                if (get("next_page").isJsonNull) null else get("next_page").asLong,
                if (get("prev_page").isJsonNull) null else get("prev_page").asLong
            ) }
        }
    }
}

class MeProgramsService(client: AnnictClient) : BaseService(client) {

    fun get(query: MeProgramsRequestQuery) : MeProgramsResponseData {
        this.client.apply { return MeProgramsResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())))) }
    }
}