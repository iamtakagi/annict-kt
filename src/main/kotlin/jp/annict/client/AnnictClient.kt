package jp.annict.client

import jp.annict.enums.Action
import jp.annict.enums.Order
import jp.annict.enums.RatingState
import jp.annict.enums.Status
import jp.annict.services.*
import jp.annict.services.me.*
import okhttp3.*

class AnnictClient(val token: String, val client: OkHttpClient = OkHttpClient()) {
    
    fun getUrlBuilder(): HttpUrl.Builder = HttpUrl.Builder().scheme("https").host("api.annict.com").addPathSegment("v1")
    fun request(requestBuilder: Request.Builder): Response = this.client.newCall(requestBuilder.header("Authorization", "Bearer $token").build()).execute()

    private val activitiesService = ActivitiesService(this)
    private val castsService = CastsService(this)
    private val charactersService = CharactersService(this)
    private val episodesService = EpisodesService(this)
    private val followersService = FollowersService(this)
    private val followingService = FollowingService(this)
    private val organizationsService = OrganizationsService(this)
    private val peopleService = PeopleService(this)
    private val recordsService = RecordsService(this)
    private val reviewsService = ReviewsService(this)
    private val seriesService = SeriesService(this)
    private val staffsService = StaffsService(this)
    private val usersService = UsersService(this)
    private val worksService = WorksService(this)
    private val meFollowingActivitiesService = MeFollowingActivitiesService(this)
    private val meProgramsService = MeProgramsService(this)
    private val meRecordsService = MeRecordsService(this)
    private val meReviewsService = MeReviewsService(this)
    private val meService = MeService(this)
    private val meStatusesService = MeStatusesService(this)
    private val meWorksService = MeWorksService(this)

    /**
     * Get activities
     *
     * @param fields
     * @param filter_user_id
     * @param filter_username
     * @param page
     * @param per_page
     * @param sort_id
     * @return
     */
    fun getActivities(fields: Array<String>? =null, filter_user_id: Long?=null, filter_username: String?=null, page: Long?=null, per_page: Long? =null, sort_id: Order? =null) : ActivitiesGetResponseData? = activitiesService.get(
        ActivitiesGetRequestQuery(
            fields,
            filter_user_id,
            filter_username,
            page,
            per_page,
            sort_id
        )
    )

    /**
     * Get casts
     *
     * @param fields
     * @param filter_ids
     * @param filter_work_id
     * @param page
     * @param per_page
     * @param sort_id
     * @param sort_sort_number
     * @return
     */
    fun getCasts(fields: Array<String>? =null, filter_ids: Array<Long>? =null, filter_work_id: Long?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_sort_number: Order?=null) : CastsGetResponseData? = castsService.get(
        CastsGetRequestQuery(
            fields,
            filter_ids,
            filter_work_id,
            page,
            per_page,
            sort_id,
            sort_sort_number
        )
    )

    /**
     * Get characters
     *
     * @param fields
     * @param filter_ids
     * @param filter_work_id
     * @param page
     * @param per_page
     * @param sort_id
     * @return
     */
    fun getCharacters(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id: Long?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : CharactersGetResponseData? = charactersService.get(
        CharactersGetRequestQuery(
            fields,
            filter_ids,
            filter_work_id,
            page,
            per_page,
            sort_id
        )
    )

    /**
     * Get episodes
     *
     * @param fields
     * @param filter_ids
     * @param filter_work_id
     * @param page
     * @param per_page
     * @param sort_id
     * @param sort_sort_number
     * @return
     */
    fun getEpisodes(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id: Long?=null, page: Long?=null, per_page: Long?=null, sort_id : Order?=null, sort_sort_number : Order?=null) : EpisodesGetResponseData? = episodesService.get(
        EpisodesGetRequestQuery(
            fields,
            filter_ids,
            filter_work_id,
            page,
            per_page,
            sort_id,
            sort_sort_number
        )
    )

    /**
     * Get followers
     *
     * @param fields
     * @param filter_user_id
     * @param filter_username
     * @param page
     * @param per_page
     * @param sort_id
     * @return
     */
    fun getFollowers(fields: Array<String>?=null, filter_user_id: Long?=null, filter_username: String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : FollowersGetResponseData? = followersService.get(
        FollowersGetRequestQuery(
            fields,
            filter_user_id,
            filter_username,
            page,
            per_page,
            sort_id
        )
    )

    /**
     * Get following
     *
     * @param fields
     * @param filter_user_id
     * @param filter_username
     * @param page
     * @param per_page
     * @param sort_id
     * @return
     */
    fun getFollowing(fields: Array<String>?=null, filter_user_id: Long?=null, filter_username: String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : FollowingGetResponseData? = followingService.get(
        FollowingGetRequestQuery(
            fields,
            filter_user_id,
            filter_username,
            page,
            per_page,
            sort_id
        )
    )

    /**
     * Get organizations
     *
     * @param fields
     * @param filter_ids
     * @param filter_work_id
     * @param page
     * @param per_page
     * @param sort_id
     * @return
     */
    fun getOrganizations(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id : Long?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : OrganizationsGetResponseData? = organizationsService.get(
        OrganizationsGetRequestQuery(
            fields,
            filter_ids,
            filter_work_id,
            page,
            per_page,
            sort_id
        )
    )

    /**
     * Get people
     *
     * @param fields
     * @param filter_ids
     * @param filter_work_id
     * @param page
     * @param per_page
     * @param sort_id
     * @return
     */
    fun getPeople(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id: Long?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : PeopleGetResponseData? = peopleService.get(
        PeopleGetRequestQuery(
            fields,
            filter_ids,
            filter_work_id,
            page,
            per_page,
            sort_id
        )
    )

    /**
     * Get records
     *
     * @param fields
     * @param filter_ids
     * @param filter_episode_id
     * @param filter_has_record_comment
     * @param page
     * @param per_page
     * @param sort_id
     * @param sort_likes_count
     * @return
     */
    fun getRecords(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_episode_id: Long?=null, filter_has_record_comment: Boolean?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_likes_count: Order?=null) : RecordsGetResponseData? = recordsService.get(
        RecordsGetRequestQuery(
            fields,
            filter_ids,
            filter_episode_id,
            filter_has_record_comment,
            page,
            per_page,
            sort_id,
            sort_likes_count
        )
    )

    /**
     * Get reviews
     *
     * @param fields
     * @param filter_ids
     * @param filter_work_id
     * @param filter_has_review_body
     * @param page
     * @param per_page
     * @param sort_id
     * @param sort_likes_count
     * @return
     */
    fun getReviews(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id: Long?=null, filter_has_review_body: Boolean?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_likes_count: Order?=null) : ReviewsGetResponseData? = reviewsService.get(
        ReviewsGetRequestQuery(
            fields,
            filter_ids,
            filter_work_id,
            filter_has_review_body,
            page,
            per_page,
            sort_id,
            sort_likes_count
        )
    )

    /**
     * Get series
     *
     * @param fields
     * @param filter_ids
     * @param filter_name
     * @param page
     * @param per_page
     * @param sort_id
     * @return
     */
    fun getSeries(fields: Array<String>?=null, filter_ids : Array<Long>?=null, filter_name: String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : SeriesGetResponseData? = seriesService.get(
        SeriesGetRequestQuery(
            fields,
            filter_ids,
            filter_name,
            page,
            per_page,
            sort_id
        )
    )

    /**
     * Get staffs
     *
     * @param fields
     * @param filter_ids
     * @param filter_work_id
     * @param page
     * @param per_page
     * @param sort_id
     * @param sort_sort_number
     * @return
     */
    fun getStaffs(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id: Long?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_sort_number: Order?=null) : StaffsGetResponseData? = staffsService.get(
        StaffsGetRequestQuery(
            fields,
            filter_ids,
            filter_work_id,
            page,
            per_page,
            sort_id,
            sort_sort_number
        )
    )

    /**
     * Get users
     *
     * @param fields
     * @param filter_user_id
     * @param filter_username
     * @param page
     * @param per_page
     * @param sort_id
     * @return
     */
    fun getUsers(fields: Array<String>?=null, filter_user_id : Long?=null, filter_username   : String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : UsersGetResponseData? = usersService.get(
        UsersGetRequestQuery(
            fields,
            filter_user_id,
            filter_username,
            page,
            per_page,
            sort_id
        )
    )

    /**
     * Get works
     *
     * @param fields
     * @param filter_ids
     * @param filter_season
     * @param filter_title
     * @param page
     * @param per_page
     * @param sort_id
     * @param sort_season
     * @param sort_watchers_count
     * @return
     */
    fun getWorks(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_season: String?=null, filter_title: String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_season: Order?=null, sort_watchers_count: Order?=null ) : WorksGetResponseData? = worksService.get(
        WorksGetRequestQuery(
            fields,
            filter_ids,
            filter_season,
            filter_title,
            page,
            per_page,
            sort_id,
            sort_season,
            sort_watchers_count
        )
    )

    /**
     * Get me following activities
     *
     * @param fields
     * @param filter_actions
     * @param filter_muted
     * @param page
     * @param per_page
     * @param sort_id
     * @return
     */
    fun getMeFollowingActivities(fields: Array<String>? =null, filter_actions: Array<Action>? =null, filter_muted: Boolean? =null, page: Long? =null, per_page: Long? =null, sort_id: Order?=null) : MeFollowingActivitiesGetResponseData? = meFollowingActivitiesService.get(
        MeFollowingActivitiesGetRequestQuery(
            fields,
            filter_actions,
            filter_muted,
            page,
            per_page,
            sort_id
        )
    )

    /**
     * Get me programs
     *
     * @param fields
     * @param filter_ids
     * @param filter_channel_ids
     * @param filter_work_ids
     * @param filter_started_at_gt
     * @param filter_started_at_lt
     * @param filter_unwatched
     * @param filter_rebroadcast
     * @param page
     * @param per_page
     * @param sort_id
     * @param sort_started_at
     * @return
     */
    fun getMePrograms(fields: Array<String>? =null, filter_ids: Array<Long>? =null, filter_channel_ids: Array<Long>? =null, filter_work_ids: Array<Long>? =null, filter_started_at_gt: String? =null, filter_started_at_lt: String? =null, filter_unwatched: Boolean? =null, filter_rebroadcast: Boolean? =null, page: Long? =null, per_page: Long? =null, sort_id: Order? =null, sort_started_at: Order? =null) : MeProgramsGetResponseData? = meProgramsService.get(
        MeProgramsGetRequestQuery(
            fields,
            filter_ids,
            filter_channel_ids,
            filter_work_ids,
            filter_started_at_gt,
            filter_started_at_lt,
            filter_unwatched,
            filter_rebroadcast,
            page,
            per_page,
            sort_id,
            sort_started_at
        )
    )

    /**
     * Post me record
     *
     * @param episode_id
     * @param comment
     * @param rating_state
     * @param share_twitter
     * @param share_facebook
     * @return
     */
    fun postMeRecord(episode_id: Long? =null, comment: String? =null, rating_state: RatingState? =null, share_twitter: Boolean? =null, share_facebook: Boolean? =null) : MeRecordsPostResponseData? = meRecordsService.post(
        MeRecordsPostRequestQuery(
            episode_id,
            comment,
            rating_state,
            share_twitter,
            share_facebook
        )
    )

    /**
     * Patch me record
     *
     * @param episode_id
     * @param comment
     * @param rating_state
     * @param share_twitter
     * @param share_facebook
     * @return
     */
    fun patchMeRecord(episode_id: Long? =null, comment: String? =null, rating_state: RatingState? =null, share_twitter: Boolean? =null, share_facebook: Boolean? =null) : MeRecordsPatchResponseData? = meRecordsService.patch(
        MeRecordsPatchRequestQuery(
            episode_id,
            comment,
            rating_state,
            share_twitter,
            share_facebook
        )
    )

    /**
     * Delete me record
     *
     * @param id
     * @return
     */
    fun deleteMeRecord(id: Long) : Boolean? = meRecordsService.delete(
        MeRecordsDeleteRequestQuery(id)
    )

    fun postMeReview(work_id: Long? =null, title: String?=null, body: String? =null, rating_animation_state: RatingState? =null, rating_music_state: RatingState? =null, rating_story_state: RatingState? =null, rating_character_state: RatingState? =null, rating_overall_state: RatingState? =null, share_twitter: Boolean? =null, share_facebook: Boolean? =null) : MeReviewsPostResponseData? = meReviewsService.post(
        MeReviewsPostRequestQuery(
            work_id,
            title,
            body,
            rating_animation_state,
            rating_music_state,
            rating_story_state,
            rating_character_state,
            rating_overall_state,
            share_twitter,
            share_facebook
        )
    )

    /**
     * Patch me review
     *
     * @param work_id
     * @param title
     * @param body
     * @param rating_animation_state
     * @param rating_music_state
     * @param rating_story_state
     * @param rating_character_state
     * @param rating_overall_state
     * @param share_twitter
     * @param share_facebook
     * @return
     */
    fun patchMeReview(work_id: Long? =null, title: String? =null, body: String? =null, rating_animation_state: RatingState? =null, rating_music_state: RatingState? =null, rating_story_state: RatingState? =null, rating_character_state: RatingState? =null, rating_overall_state: RatingState? =null, share_twitter: Boolean? =null, share_facebook: Boolean? =null) : MeReviewsPatchResponseData? = meReviewsService.patch(
        MeReviewsPatchRequestQuery(
            work_id,
            title,
            body,
            rating_animation_state,
            rating_music_state,
            rating_story_state,
            rating_character_state,
            rating_overall_state,
            share_twitter,
            share_facebook
        )
    )

    /**
     * Delete me review
     *
     * @param id
     * @return
     */
    fun deleteMeReview(id: Long? =null) : Boolean? = meReviewsService.delete(
        MeReviewsDeleteRequestQuery(
                id
        )
    )

    /**
     * Get me
     *
     * @param fields
     * @return
     */
    fun getMe(fields: Array<String>? =null) : MeGetResponseData? = meService.get(
        MeGetRequestQuery(
                fields
        )
    )

    /**
     * Post me status
     *
     * @param work_id
     * @param kind
     * @return
     */
    fun postMeStatus(work_id: Long? =null, kind: Status? =null) : Boolean? = meStatusesService.post(
        MeStatuesPostRequestQuery(
                work_id,
                kind
        )
    )

    /**
     * Get me works
     *
     * @param fields
     * @param filter_ids
     * @param filter_season
     * @param filter_title
     * @param page
     * @param per_page
     * @param sort_id
     * @param sort_season
     * @param sort_watchers_count
     * @param filter_status
     * @return
     */
    fun getMeWorks(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_season: String?=null, filter_title: String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_season : Order?=null, sort_watchers_count: Order?=null, filter_status: Status?=null) : MeWorksGetResponseData? = meWorksService.get(
        MeWorksGetRequestQuery(
            fields,
            filter_ids,
            filter_season,
            filter_title,
            page,
            per_page,
            sort_id,
            sort_season,
            sort_watchers_count,
            filter_status
        )
    )
}