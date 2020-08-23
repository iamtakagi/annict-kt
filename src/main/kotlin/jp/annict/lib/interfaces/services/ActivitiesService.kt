package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface ActivitiesService : AnnictService { fun get(query: ActivitiesGetRequestQuery) : ActivitiesGetResponseData }

interface ActivitiesGetRequestQuery: RequestQuery

interface ActivitiesGetResponseData: ResponseData