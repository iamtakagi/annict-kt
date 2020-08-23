package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface EpisodesService : AnnictService { fun get(query: EpisodesGetRequestQuery) : EpisodesGetResponseData }

interface EpisodesGetRequestQuery: RequestQuery

interface EpisodesGetResponseData: ResponseData