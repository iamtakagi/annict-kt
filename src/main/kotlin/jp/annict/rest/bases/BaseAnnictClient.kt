package jp.annict.rest.bases

import jp.annict.rest.interfaces.AnnictClient
import okhttp3.OkHttpClient

abstract class BaseAnnictClient(val client: OkHttpClient) : AnnictClient