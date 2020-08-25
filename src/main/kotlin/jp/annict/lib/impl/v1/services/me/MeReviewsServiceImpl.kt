package jp.annict.lib.impl.v1.services.me

import com.google.gson.reflect.TypeToken
import jp.annict.lib.interfaces.IAnnictClient
import jp.annict.lib.interfaces.services.me.*
import jp.annict.lib.interfaces.services.me.MeReviewsService
import jp.annict.lib.utils.JsonUtil
import jp.annict.lib.impl.v1.enums.RatingState
import jp.annict.lib.impl.v1.models.Review
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeReviewsPostRequestQueryImpl (
    val work_id: Long? =null,
    val title: String? =null,
    val body: String? =null,
    val rating_animation_state: RatingState? =null,
    val rating_music_state: RatingState? =null,
    val rating_story_state: RatingState? =null,
    val rating_character_state: RatingState? =null,
    val rating_overall_state: RatingState? =null,
    val share_twitter: Boolean? =null,
    val share_facebook: Boolean? =null) : MeReviewsPostRequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("/me/reviews")

            if(work_id != null) { addQueryParameter("work_id", work_id.toString()) }
            if(title != null && title.isNotEmpty()) { addQueryParameter("title", title) }
            if(body != null && body.isNotEmpty()) { addQueryParameter("body", body) }
            if(rating_animation_state != null) { addQueryParameter("rating_animation_state", rating_animation_state.toString()) }
            if(rating_music_state != null) { addQueryParameter("rating_music_state", rating_music_state.toString()) }
            if(rating_story_state != null) { addQueryParameter("rating_story_state", rating_story_state.toString()) }
            if(rating_character_state != null) { addQueryParameter("rating_character_state", rating_character_state.toString()) }
            if(rating_overall_state != null) { addQueryParameter("rating_overall_state", rating_overall_state.toString()) }
            if(share_twitter != null) { addQueryParameter("share_twitter", share_twitter.toString()) }
            if(share_facebook != null) { addQueryParameter("share_facebook", share_facebook.toString()) }

        }.build()
    }
}

data class MeReviewsPostResponseDataImpl(val review: Review?) : MeReviewsPostResponseData {

    constructor() : this(null)

    override fun toDataClass(response: Response): MeReviewsPostResponseData {
        return MeReviewsPostResponseDataImpl(JsonUtil.GSON.fromJson(
            JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject,
            object : TypeToken<Review>() {}.type)
        )
    }
}

data class MeReviewsPatchRequestQueryImpl (
    val work_id: Long? =null,
    val title: String? =null,
    val body: String? =null,
    val rating_animation_state: RatingState? =null,
    val rating_music_state: RatingState? =null,
    val rating_story_state: RatingState? =null,
    val rating_character_state: RatingState? =null,
    val rating_overall_state: RatingState? =null,
    val share_twitter: Boolean? =null,
    val share_facebook: Boolean? =null) : MeReviewsPatchRequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("/me/reviews")

            if(work_id != null) { addQueryParameter("work_id", work_id.toString()) }
            if(title != null && title.isNotEmpty()) { addQueryParameter("title", title) }
            if(body != null && body.isNotEmpty()) { addQueryParameter("body", body) }
            if(rating_animation_state != null) { addQueryParameter("rating_animation_state", rating_animation_state.toString()) }
            if(rating_music_state != null) { addQueryParameter("rating_music_state", rating_music_state.toString()) }
            if(rating_story_state != null) { addQueryParameter("rating_story_state", rating_story_state.toString()) }
            if(rating_character_state != null) { addQueryParameter("rating_character_state", rating_character_state.toString()) }
            if(rating_overall_state != null) { addQueryParameter("rating_overall_state", rating_overall_state.toString()) }
            if(share_twitter != null) { addQueryParameter("share_twitter", share_twitter.toString()) }
            if(share_facebook != null) { addQueryParameter("share_facebook", share_facebook.toString()) }

        }.build()
    }
}

data class MeReviewsPatchResponseDataImpl(val review: Review?) : MeReviewsPatchResponseData {

    constructor() : this(null)

    override fun toDataClass(response: Response): MeReviewsPatchResponseData {
        return MeReviewsPatchResponseDataImpl(JsonUtil.GSON.fromJson(
            JsonUtil.JSON_PARSER.parse(response.body?.string()).asJsonObject,
            object : TypeToken<Review>() {}.type)
        )
    }
}

data class MeReviewsDeleteRequestQueryImpl(val id: Long? =null) : MeReviewsDeleteRequestQuery {

    override fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("/me/reviews/${id}")
        }.build()
    }
}

class MeReviewsServiceImpl (val client: IAnnictClient) : MeReviewsService {

    /**
     * レビュー投稿 [write scope]
     */
    override fun post(query: MeReviewsPostRequestQuery): MeReviewsPostResponseData {
        this.client.apply {
            return MeReviewsPostResponseDataImpl().toDataClass(
                request(
                    Request.Builder().url(query.url(getUrlBuilder())).method("post", null)
                )
            )
        }
    }

    /**
     * レビュー編集 [write scope]
     */
    override fun patch(query: MeReviewsPatchRequestQuery): MeReviewsPatchResponseData {
        this.client.apply {
            return MeReviewsPatchResponseDataImpl().toDataClass(
                request(
                    Request.Builder().url(query.url(getUrlBuilder())).method("patch", null)
                )
            )
        }
    }

    /**
     * レビュー削除 [write scope]
     */
    override fun delete(query: MeReviewsDeleteRequestQuery): Boolean {
        this.client.apply {
            return (request(Request.Builder().url(query.url(getUrlBuilder())).method("delete", null)).code == 204)
        }
    }
}