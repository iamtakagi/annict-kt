package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface FollowingService : AnnictService { fun get(query: FollowingGetRequestQuery) : FollowingGetResponseData }

interface FollowingGetRequestQuery: RequestQuery

interface FollowingGetResponseData: ResponseData