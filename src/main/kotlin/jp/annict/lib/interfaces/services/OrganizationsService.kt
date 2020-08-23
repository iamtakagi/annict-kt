package jp.annict.lib.interfaces.services

import jp.annict.lib.interfaces.AnnictService
import jp.annict.lib.interfaces.RequestQuery
import jp.annict.lib.interfaces.ResponseData

interface OrganizationsService : AnnictService { fun get(query: OrganizationsGetRequestQuery) : OrganizationsGetResponseData }

interface OrganizationsGetRequestQuery: RequestQuery

interface OrganizationsGetResponseData: ResponseData