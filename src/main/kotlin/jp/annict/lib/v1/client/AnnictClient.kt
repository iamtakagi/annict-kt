package jp.annict.lib.v1.client

import jp.annict.lib.bases.BaseAnnictClient
import jp.annict.lib.enums.AnnictVersion
import jp.annict.lib.v1.enums.Action
import jp.annict.lib.v1.enums.Order
import jp.annict.lib.v1.enums.RatingState
import jp.annict.lib.v1.enums.Status
import jp.annict.lib.v1.services.*
import jp.annict.lib.v1.services.me.*
import okhttp3.*
import jp.annict.lib.v1.services.CharactersGetRequestQuery as CharactersGetRequestQuery

class AnnictClient(val token: String, httpClient: OkHttpClient = OkHttpClient()) : BaseAnnictClient(httpClient) {

    override fun getVersion(): AnnictVersion = AnnictVersion.V_1

    override fun getUrlBuilder(): HttpUrl.Builder = HttpUrl.Builder().scheme("https").host("api.annict.com").addPathSegment("v1")

    override fun request(requestBuilder: Request.Builder): Response = this.client.newCall(requestBuilder.header("Authorization", "Bearer $token").build()).execute()

    val activitiesService = ActivitiesService(this)

    val castsService = CastsService(this)

    val charactersService = CharactersService(this)

    val episodesService = EpisodesService(this)

    val followersService = FollowersService(this)

    val followingService = FollowingService(this)

    val organizationsService = OrganizationsService(this)

    val peopleService = PeopleService(this)

    val recordsService = RecordsService(this)

    val reviewsService = ReviewsService(this)

    val seriesService = SeriesService(this)

    val staffsService = StaffsService(this)

    val usersService = UsersService(this)

    val worksService = WorksService(this)

    val meFollowingActivitiesService = MeFollowingActivitiesService(this)

    val meProgramsService = MeProgramsService(this)

    val meRecordsService = MeRecordsService(this)

    val meReviewsService = MeReviewsService(this)

    val meService = MeService(this)

    val meStatusesService = MeStatusesService(this)

    val meWorksService = MeWorksService(this)

    fun getActivities(fields: Array<String>? =null, filter_user_id: Long?=null, filter_username: String?=null, page: Long?=null, per_page: Long? =null, sort_id: Order? =null) : ActivitiesGetResponseData = activitiesService.get(
        ActivitiesGetRequestQuery(fields, filter_user_id, filter_username, page, per_page, sort_id)
    )

    fun getCasts(fields: Array<String>? =null, filter_ids: Array<Long>? =null, filter_work_id: Long?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_sort_number: Order?=null) : CastsGetResponseData = castsService.get(
        CastsGetRequestQuery(fields, filter_ids, filter_work_id, page, per_page, sort_id, sort_sort_number)
    )

    fun getCharacters(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id: Long?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : CharactersGetResponseData = charactersService.get(
        CharactersGetRequestQuery(fields, filter_ids, filter_work_id, page, per_page, sort_id)
    )

    fun getEpisodes(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id: Long?=null, page: Long?=null, per_page: Long?=null, sort_id : Order?=null, sort_sort_number : Order?=null) : EpisodesGetResponseData = episodesService.get(
        EpisodesGetRequestQuery(fields, filter_ids, filter_work_id, page, per_page, sort_id, sort_sort_number)
    )

    fun getFollowers(fields: Array<String>?=null, filter_user_id: Long?=null, filter_username: String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : FollowersGetResponseData = followersService.get(
        FollowersGetRequestQuery(fields, filter_user_id, filter_username, page, per_page, sort_id)
    )

    fun getFollowing(fields: Array<String>?=null, filter_user_id: Long?=null, filter_username: String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : FollowingGetResponseData = followingService.get(
        FollowingGetRequestQuery(fields, filter_user_id, filter_username, page, per_page, sort_id)
    )

    fun getOrganizations(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id : Long?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : OrganizationsGetResponseData = organizationsService.get(
        OrganizationsGetRequestQuery(fields, filter_ids, filter_work_id, page, per_page, sort_id)
    )

    fun getPeople(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id: Long?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : PeopleGetResponseData = peopleService.get(
        PeopleGetRequestQuery(fields, filter_ids, filter_work_id, page, per_page, sort_id)
    )

    fun getRecords(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_episode_id: Long?=null, filter_has_record_comment: Boolean?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_likes_count: Order?=null) : RecordsGetResponseData = recordsService.get(
        RecordsGetRequestQuery(fields, filter_ids, filter_episode_id, filter_has_record_comment, page, per_page, sort_id, sort_likes_count)
    )

    fun getReviews(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id: Long?=null, filter_has_review_body: Boolean?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_likes_count: Order?=null) : ReviewsGetResponseData = reviewsService.get(
        ReviewsGetRequestQuery(fields, filter_ids, filter_work_id, filter_has_review_body, page, per_page, sort_id, sort_likes_count)
    )

    fun getSeries(fields: Array<String>?=null, filter_ids : Array<Long>?=null, filter_name: String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : SeriesGetResponseData = seriesService.get(
        SeriesGetRequestQuery(fields, filter_ids, filter_name, page, per_page, sort_id)
    )

    fun getStaffs(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_work_id: Long?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_sort_number: Order?=null) : StaffsGetResponseData = staffsService.get(
        StaffsGetRequestQuery(fields, filter_ids, filter_work_id, page, per_page, sort_id, sort_sort_number)
    )

    fun getUsers(fields: Array<String>?=null, filter_user_id : Long?=null, filter_username   : String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null) : UsersGetResponseData = usersService.get(
        UsersGetRequestQuery(fields, filter_user_id, filter_username, page, per_page, sort_id)
    )

    fun getWorks(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_season: String?=null, filter_title: String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_season: Order?=null, sort_watchers_count: Order?=null ) : WorksGetResponseData = worksService.get(
        WorksGetRequestQuery(fields, filter_ids, filter_season, filter_title, page, per_page, sort_id, sort_season, sort_watchers_count)
    )

    fun getMeFollowingActivities(fields: Array<String>? =null, filter_actions: Array<Action>? =null, filter_muted: Boolean? =null, page: Long? =null, per_page: Long? =null, sort_id: Order?=null) : MeFollowingActivitiesGetResponseData = meFollowingActivitiesService.get(
        MeFollowingActivitiesGetRequestQuery(fields, filter_actions, filter_muted, page, per_page, sort_id)
    )

    fun getMePrograms(fields: Array<String>? =null, filter_ids: Array<Long>? =null, filter_channel_ids: Array<Long>? =null, filter_work_ids: Array<Long>? =null, filter_started_at_gt: String? =null, filter_started_at_lt: String? =null, filter_unwatched: Boolean? =null, filter_rebroadcast: Boolean? =null, page: Long? =null, per_page: Long? =null, sort_id: Order? =null, sort_started_at: Order? =null) : MeProgramsGetResponseData = meProgramsService.get(
        MeProgramsGetRequestQuery(fields, filter_ids, filter_channel_ids, filter_work_ids, filter_started_at_gt, filter_started_at_lt, filter_unwatched, filter_rebroadcast, page, per_page, sort_id, sort_started_at)
    )

    fun postMeRecord(episode_id: Long? =null, comment: String? =null, rating_state: RatingState? =null, share_twitter: Boolean? =null, share_facebook: Boolean? =null) : MeRecordsPostResponseData = meRecordsService.post(
        MeRecordsPostRequestQuery(episode_id, comment, rating_state, share_twitter, share_facebook)
    )

    fun patchMeRecord(episode_id: Long? =null, comment: String? =null, rating_state: RatingState? =null, share_twitter: Boolean? =null, share_facebook: Boolean? =null) : MeRecordsPatchResponseData = meRecordsService.patch(
        MeRecordsPatchRequestQuery(episode_id, comment, rating_state, share_twitter, share_facebook)
    )

    fun deleteMeRecord(id: Long) : Boolean = meRecordsService.delete(
        MeRecordsDeleteRequestQuery(id)
    )

    fun postMeReview(work_id: Long? =null, title: String?=null, body: String? =null, rating_animation_state: RatingState? =null, rating_music_state: RatingState? =null, rating_story_state: RatingState? =null, rating_character_state: RatingState? =null, rating_overall_state: RatingState? =null, share_twitter: Boolean? =null, share_facebook: Boolean? =null) : MeReviewsPostResponseData = meReviewsService.post(
        MeReviewsPostRequestQuery(work_id, title, body, rating_animation_state, rating_music_state, rating_story_state, rating_character_state, rating_overall_state, share_twitter, share_facebook)
    )

    fun patchMeReview(work_id: Long? =null, title: String? =null, body: String? =null, rating_animation_state: RatingState? =null, rating_music_state: RatingState? =null, rating_story_state: RatingState? =null, rating_character_state: RatingState? =null, rating_overall_state: RatingState? =null, share_twitter: Boolean? =null, share_facebook: Boolean? =null) : MeReviewsPatchResponseData = meReviewsService.patch(
        MeReviewsPatchRequestQuery(work_id, title, body, rating_animation_state, rating_music_state, rating_story_state, rating_character_state, rating_overall_state, share_twitter, share_facebook)
    )

    fun deleteMeReview(id: Long? =null) : Boolean = meReviewsService.delete(
        MeReviewsDeleteRequestQuery(id)
    )

    fun getMe(fields: Array<String>? =null) : MeGetResponseData = meService.get(
        MeGetRequestQuery(fields)
    )

    fun postMeStatus(work_id: Long? =null, kind: Status? =null) : Boolean = meStatusesService.post(
        MeStatuesPostRequestQuery(work_id, kind)
    )

    fun getMeWorks(fields: Array<String>?=null, filter_ids: Array<Long>?=null, filter_season: String?=null, filter_title: String?=null, page: Long?=null, per_page: Long?=null, sort_id: Order?=null, sort_season : Order?=null, sort_watchers_count: Order?=null, filter_status: Status?=null) : MeWorksGetResponseData = meWorksService.get(
        MeWorksGetRequestQuery(fields, filter_ids, filter_season, filter_title, page, per_page, sort_id, sort_season, sort_watchers_count, filter_status)
    )
}