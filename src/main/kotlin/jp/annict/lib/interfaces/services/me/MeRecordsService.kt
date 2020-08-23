package jp.annict.lib.interfaces.services.me

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface MeRecordsService : AnnictService {
    fun post(query: MeRecordsPostRequestQuery) : MeRecordsPostResponseData
    fun patch(query: MeRecordsPatchRequestQuery) : MeRecordsPatchResponseData
    fun delete(query: MeRecordsDeleteRequestQuery) : Boolean
}

interface MeRecordsPostRequestQuery: RequestQuery

interface MeRecordsPostResponseData: ResponseData

interface MeRecordsPatchRequestQuery: RequestQuery

interface MeRecordsPatchResponseData: ResponseData

interface MeRecordsDeleteRequestQuery: RequestQuery
