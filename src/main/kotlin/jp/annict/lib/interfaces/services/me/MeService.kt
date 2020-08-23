package jp.annict.lib.interfaces.services.me

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface MeService : AnnictService { fun get(query: MeGetRequestQuery) : MeGetResponseData }

interface MeGetRequestQuery: RequestQuery

interface MeGetResponseData: ResponseData