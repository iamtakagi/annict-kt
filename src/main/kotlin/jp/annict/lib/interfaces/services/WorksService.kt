package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface WorksService : AnnictService { fun get(query: WorksGetRequestQuery) : WorksGetResponseData }

interface WorksGetRequestQuery: RequestQuery

interface WorksGetResponseData: ResponseData