package jp.annict.lib.interfaces.services.me

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface MeFollowingActivitiesService : AnnictService { fun get(query: MeFollowingActivitiesGetRequestQuery) : MeFollowingActivitiesGetResponseData }

interface MeFollowingActivitiesGetRequestQuery: RequestQuery

interface MeFollowingActivitiesGetResponseData: ResponseData