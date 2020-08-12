package jp.annict.rest.v1.services

import jp.annict.rest.v1.AnnictClient
import jp.annict.rest.v1.type.Order
import okhttp3.Request
import okhttp3.Response

data class Episode(
    val id            : Int,
    val number        : Int,
    val number_text   : String,
    val sort_number   : Int,
    val title         : String,
    val records_count : Int,
    val work          : Work,
    val prev_episode  : Episode?,
    val next_episode  : Episode?
)

data class EpisodesGetRequestQuery (
    val fields           : String?,
    val filter_ids       : String?,
    val filter_work_id   : Int?,
    val page             : Int?,
    val per_page         : Int?,
    val sort_id          : Order?,
    val sort_sort_number : Order?
)

class EpisodeService(val client: AnnictClient) {

    fun get(query: EpisodesGetRequestQuery) : Response {
        this.client.apply {
            val urlBuilder = getUrlBuilder().apply {
                addEncodedPathSegment("episodes")

                query.apply {
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
                }
            }

            return request(Request.Builder().url(urlBuilder.build()))
        }
    }
}