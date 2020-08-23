package jp.annict.lib.interfaces.services.me

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface MeReviewsService : AnnictService {
    fun post(query: MeReviewsPostRequestQuery) : MeReviewsPostResponseData
    fun patch(query: MeReviewsPatchRequestQuery) : MeReviewsPatchResponseData
    fun delete(query: MeReviewsDeleteRequestQuery) : Boolean
}

interface MeReviewsPostRequestQuery: RequestQuery

interface MeReviewsPostResponseData: ResponseData

interface MeReviewsPatchRequestQuery: RequestQuery

interface MeReviewsPatchResponseData: ResponseData

interface MeReviewsDeleteRequestQuery: RequestQuery
