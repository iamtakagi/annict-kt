package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface FollowersService : AnnictService { fun get(query: FollowersGetRequestQuery) : FollowersGetResponseData }

interface FollowersGetRequestQuery: RequestQuery

interface FollowersGetResponseData: ResponseData