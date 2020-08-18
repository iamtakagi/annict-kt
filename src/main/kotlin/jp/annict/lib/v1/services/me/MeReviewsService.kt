package jp.annict.lib.v1.services.me

import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import jp.annict.lib.bases.BaseRequestData
import jp.annict.lib.bases.BaseService
import jp.annict.lib.interfaces.AnnictClient
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.v1.enums.RatingState
import jp.annict.lib.v1.models.Review
import jp.annict.lib.v1.models.User
import jp.annict.lib.v1.services.TokenResponseData
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

data class MeReviewsPostRequestQuery(
    val work_id: Long?,
    val title: String?,
    val body: String?,
    val rating_animation_state: RatingState?,
    val rating_music_state: RatingState?,
    val rating_story_state: RatingState?,
    val rating_character_state: RatingState?,
    val rating_overall_state: RatingState?,
    val share_twitter: Boolean?,
    val share_facebook: Boolean?) : RequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("/me/reviews")

            if(work_id != null) { addQueryParameter("work_id", work_id.toString()) }
            if(title != null && title.isNotEmpty()) { addQueryParameter("title", title) }
            if(body != null && body.isNotEmpty()) { addQueryParameter("body", body) }
            if(rating_animation_state != null) { addQueryParameter("rating_animation_state", rating_animation_state.name) }
            if(rating_music_state != null) { addQueryParameter("rating_music_state", rating_music_state.name) }
            if(rating_story_state != null) { addQueryParameter("rating_story_state", rating_story_state.name) }
            if(rating_character_state != null) { addQueryParameter("rating_character_state", rating_character_state.name) }
            if(rating_overall_state != null) { addQueryParameter("rating_overall_state", rating_overall_state.name) }
            if(share_twitter != null) { addQueryParameter("share_twitter", share_twitter.toString()) }
            if(share_facebook != null) { addQueryParameter("share_facebook", share_facebook.toString()) }

        }.build()
    }
}

data class MeReviewsPostResponseData(val review: Review?) : ResponseData<MeReviewsPostResponseData> {

    constructor() : this(null)

    override fun toDataClass(response: Response): MeReviewsPostResponseData {
        return MeReviewsPostResponseData(JsonUtil.GSON.fromJson(
            JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject,
            object : TypeToken<Review>() {}.type)
        )
    }

}


class MeReviewsService (client: AnnictClient) : BaseService(client) {

    /**
     * レビュー投稿
     */
    fun post(query: MeReviewsPostRequestQuery) : MeReviewsPostResponseData {
        this.client.apply {
            return MeReviewsPostResponseData().toDataClass(request(Request.Builder().url(query.url(getUrlBuilder())).method("post", null)))
        }
    }

    /**
     * レビュー編集
     */
    fun patch() {

    }

    /**
     * レビュー削除
     */
    fun delete() {

    }


}