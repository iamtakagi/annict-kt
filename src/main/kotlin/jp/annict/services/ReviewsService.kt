package jp.annict.services

import com.google.gson.reflect.TypeToken
import jp.annict.client.AnnictClient
import jp.annict.utils.JsonUtil
import jp.annict.enums.Order
import jp.annict.models.Review
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class ReviewsGetRequestQuery (
    val fields           : Array<String>?=null,
    val filter_ids       : Array<Long>?=null,
    val filter_work_id       : Long?=null,
    val filter_has_review_body : Boolean?=null,
    val page                : Long?=null,
    val per_page            : Long?=null,
    val sort_id             : Order?=null,
    val sort_likes_count         : Order?=null
) {

    fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("reviews")

            if(fields != null && fields.isNotEmpty()) { addQueryParameter("fields", fields.joinToString(separator = ",")) }
            if(filter_ids != null && filter_ids.isNotEmpty()) { addQueryParameter("filter_ids", filter_ids.joinToString(separator = ",")) }
            if(filter_work_id != null) { addQueryParameter("filter_work_id", filter_work_id.toString()) }
            if(filter_has_review_body != null) { addQueryParameter("filter_has_review_body", filter_has_review_body.toString()) }
            if(page != null) { addQueryParameter("page", page.toString()) }
            if(per_page != null) { addQueryParameter("per_page", per_page.toString()) }
            if(sort_id != null) { addQueryParameter("sort_id", sort_id.toString()) }
            if(sort_likes_count != null) { addQueryParameter("sort_likes_count", sort_likes_count.toString()) }

        }.build()
    }
}

data class ReviewsGetResponseData (
    val reviews: Array<Review>?,
    val total_count: Long?,
    val next_page: Long?,
    val prev_page: Long?
)  {

    constructor() : this(null, null, null, null)

     fun toDataClass(response: Response): ReviewsGetResponseData {
        response.apply {
            JsonUtil.JSON_PARSER.parse(body?.string()).asJsonObject.apply {
                return ReviewsGetResponseData(
                    JsonUtil.GSON.fromJson(
                        getAsJsonArray("reviews"),
                        object : TypeToken<Array<Review>>() {}.type
                    ),
                    if (get("total_count").isJsonNull) null else get("total_count").asLong,
                    if (get("next_page").isJsonNull) null else get("next_page").asLong,
                    if (get("prev_page").isJsonNull) null else get("prev_page").asLong
                )
            }
        }
    }

}

class ReviewsService(val client: AnnictClient) {

     fun get(query: ReviewsGetRequestQuery) : ReviewsGetResponseData {
        this.client.apply {
            return ReviewsGetResponseData()
                .toDataClass(request(Request.Builder().url(query.url(getUrlBuilder()))))
        }
    }
}