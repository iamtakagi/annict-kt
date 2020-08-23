package jp.annict.lib.interfaces.services.me

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface MeProgramsService : AnnictService { fun get(query: MeProgramsGetRequestQuery) : MeProgramsGetResponseData }

interface MeProgramsGetRequestQuery: RequestQuery

interface MeProgramsGetResponseData: ResponseData