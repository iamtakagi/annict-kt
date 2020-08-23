package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface RecordsService : AnnictService { fun get(query: RecordsGetRequestQuery) : RecordsGetResponseData }

interface RecordsGetRequestQuery: RequestQuery

interface RecordsGetResponseData: ResponseData