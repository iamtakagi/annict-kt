package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface CastsService : AnnictService { fun get(query: CastsGetRequestQuery) : CastsGetResponseData }

interface CastsGetRequestQuery: RequestQuery

interface CastsGetResponseData: ResponseData