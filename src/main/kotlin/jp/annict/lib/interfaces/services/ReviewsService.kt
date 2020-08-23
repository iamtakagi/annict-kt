package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface ReviewsService : AnnictService { fun get(query: ReviewsGetRequestQuery) : ReviewsGetResponseData }

interface ReviewsGetRequestQuery: RequestQuery

interface ReviewsGetResponseData: ResponseData