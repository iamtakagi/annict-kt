package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface StaffsService : AnnictService { fun get(query: StaffsGetRequestQuery) : StaffsGetResponseData }

interface StaffsGetRequestQuery: RequestQuery

interface StaffsGetResponseData: ResponseData