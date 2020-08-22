package jp.annict.lib.v1.client

import jp.annict.lib.bases.BaseAnnictClient
import jp.annict.lib.enums.AnnictVersion
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

    fun getActivities(query: ActivitiesGetRequestQuery) : ActivitiesGetResponseData = activitiesService.get(query)

    fun getCasts(query: CastsGetRequestQuery) : CastsGetResponseData = castsService.get(query)

    fun getCharacters(query: CharactersGetRequestQuery) : CharactersGetResponseData = charactersService.get(query)

    fun getEpisodes(query: EpisodesGetRequestQuery) : EpisodesGetResponseData = episodesService.get(query)

    fun getFollowers(query: FollowersGetRequestQuery) : FollowersGetResponseData = followersService.get(query)

    fun getFollowing(query: FollowingGetRequestQuery) : FollowingGetResponseData = followingService.get(query)

    fun getOrganizations(query: OrganizationsGetRequestQuery) : OrganizationsGetResponseData = organizationsService.get(query)

    fun getPeople(query: PeopleGetRequestQuery) : PeopleGetResponseData = peopleService.get(query)

    fun getRecords(query: RecordsGetRequestQuery) : RecordsGetResponseData = recordsService.get(query)

    fun getReviews(query: ReviewsGetRequestQuery) : ReviewsGetResponseData = reviewsService.get(query)

    fun getSeries(query: SeriesGetRequestQuery) : SeriesGetResponseData = seriesService.get(query)

    fun getStaffs(query: StaffsGetRequestQuery) : StaffsGetResponseData = staffsService.get(query)

    fun getUsers(query: UsersGetRequestQuery) : UsersGetResponseData = usersService.get(query)

    fun getWorks(query: WorksGetRequestQuery) : WorksGetResponseData = worksService.get(query)

    fun getMeFollowingActivities(query: MeFollowingActivitiesGetRequestQuery) : MeFollowingActivitiesGetResponseData = meFollowingActivitiesService.get(query)

    fun getMePrograms(query: MeProgramsGetRequestQuery) : MeProgramsGetResponseData = meProgramsService.get(query)

    fun postMeRecord(query: MeRecordsPostRequestQuery) : MeRecordsPostResponseData = meRecordsService.post(query)

    fun patchMeRecord(query: MeRecordsPatchRequestQuery) : MeRecordsPatchResponseData = meRecordsService.patch(query)

    fun deleteMeRecord(query: MeRecordsDeleteRequestQuery) : Boolean = meRecordsService.delete(query)

    fun postMeReview(query: MeReviewsPostRequestQuery) : MeReviewsPostResponseData = meReviewsService.post(query)

    fun patchMeReview(query: MeReviewsPatchRequestQuery) : MeReviewsPatchResponseData = meReviewsService.patch(query)

    fun deleteMeReview(query: MeReviewsDeleteRequestQuery) : Boolean = meReviewsService.delete(query)

    fun getMe(query: MeGetRequestQuery) : MeGetResponseData = meService.get(query)

    fun postMeStatus(query: MeStatuesPostRequestQuery) : Boolean = meStatusesService.post(query)

    fun getMeWorks(query: MeWorksGetRequestQuery) : MeWorksGetResponseData = meWorksService.get(query)
}