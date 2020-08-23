package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface PeopleService : AnnictService { fun get(query: PeopleGetRequestQuery) : PeopleGetResponseData }

interface PeopleGetRequestQuery: RequestQuery

interface PeopleGetResponseData: ResponseData