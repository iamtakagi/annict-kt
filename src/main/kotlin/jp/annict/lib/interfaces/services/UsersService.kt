package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface UsersService : AnnictService { fun get(query: UsersGetRequestQuery) : UsersGetResponseData }

interface UsersGetRequestQuery: RequestQuery

interface UsersGetResponseData: ResponseData