package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface CharactersService : AnnictService { fun get(query: CharactersGetRequestQuery) : CharactersGetResponseData }

interface CharactersGetRequestQuery: RequestQuery

interface CharactersGetResponseData: ResponseData