package jp.annict.lib.interfaces.services.me

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery

interface MeStatusesService : AnnictService { fun post(query: MeStatuesPostRequestQuery) : Boolean }

interface MeStatuesPostRequestQuery: RequestQuery