package com.github.iamtakagi.kannict.services.me

import com.github.iamtakagi.kannict.client.AnnictClient
import com.github.iamtakagi.kannict.enums.RatingState
import com.github.iamtakagi.kannict.exception.AnnictError
import com.github.iamtakagi.kannict.models.Review
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

data class MeReviewsPostRequestQuery (
    val work_id: Long? =null,
    val title: String? =null,
    val body: String? =null,
    val rating_animation_state: RatingState? =null,
    val rating_music_state: RatingState? =null,
    val rating_story_state: RatingState? =null,
    val rating_character_state: RatingState? =null,
    val rating_overall_state: RatingState? =null,
    val share_twitter: Boolean? =null,
    val share_facebook: Boolean? =null) {

    internal fun url(builder: HttpUrl.Builder): HttpUrl {
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

@Serializable
data class MeReviewsPostResponseData(val review: Review? = null) {

    constructor() : this(null)

    internal fun parse(response: Response): MeReviewsPostResponseData? {
         response.apply {
             if (response.code != 200) {
                 return throw AnnictError(response.message)
             }
             return body?.string()?.let { Json { isLenient = true }.decodeFromString<MeReviewsPostResponseData>(it) }
         }
    }
}

data class MeReviewsPatchRequestQuery (
    val work_id: Long? =null,
    val title: String? =null,
    val body: String? =null,
    val rating_animation_state: RatingState? =null,
    val rating_music_state: RatingState? =null,
    val rating_story_state: RatingState? =null,
    val rating_character_state: RatingState? =null,
    val rating_overall_state: RatingState? =null,
    val share_twitter: Boolean? =null,
    val share_facebook: Boolean? =null) {


    internal fun url(builder: HttpUrl.Builder): HttpUrl {
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

@Serializable
data class MeReviewsPatchResponseData(val review: Review? = null) {

    constructor() : this(null)

    internal fun parse(response: Response): MeReviewsPatchResponseData? {
        response.apply {
            if (response.code != 200) {
                return throw AnnictError(response.message)
            }
            return body?.string()?.let { Json { isLenient = true }.decodeFromString<MeReviewsPatchResponseData>(it) }
        }
    }
}

data class MeReviewsDeleteRequestQuery(val id: Long? =null) {

    internal fun url(builder: HttpUrl.Builder): HttpUrl {
        return builder.apply {
            addPathSegment("/me/reviews/${id}")
        }.build()
    }
}

class MeReviewsService (val client: AnnictClient) {

    /**
     * レビュー投稿 [write scope]
     */
    internal fun post(query: MeReviewsPostRequestQuery): MeReviewsPostResponseData? {
        this.client.apply {
            return MeReviewsPostResponseData().parse(
                request(
                    Request.Builder().url(query.url(getUrlBuilder())).method("post", null)
                )
            )
        }
    }

    /**
     * レビュー編集 [write scope]
     */
    internal fun patch(query: MeReviewsPatchRequestQuery): MeReviewsPatchResponseData? {
        this.client.apply {
            return MeReviewsPatchResponseData().parse(
                request(
                    Request.Builder().url(query.url(getUrlBuilder())).method("patch", null)
                )
            )
        }
    }

    /**
     * レビュー削除 [write scope]
     */
    internal fun delete(query: MeReviewsDeleteRequestQuery): Boolean? {
        this.client.apply {
            return (request(Request.Builder().url(query.url(getUrlBuilder())).method("delete", null)).code == 204)
        }
    }
}