package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface SeriesService : AnnictService { fun get(query: SeriesGetRequestQuery) : SeriesGetResponseData }

interface SeriesGetRequestQuery: RequestQuery

interface SeriesGetResponseData: ResponseData