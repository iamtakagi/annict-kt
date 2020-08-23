package jp.annict.lib.interfaces.services.me

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface MeWorksService : AnnictService { fun get(query: MeWorksGetRequestQuery) : MeWorksGetResponseData }

interface MeWorksGetRequestQuery: RequestQuery

interface MeWorksGetResponseData: ResponseData