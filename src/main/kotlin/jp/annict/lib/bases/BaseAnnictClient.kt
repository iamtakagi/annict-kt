package jp.annict.lib.bases

import jp.annict.lib.interfaces.AnnictClient
import okhttp3.OkHttpClient

abstract class BaseAnnictClient(val client: OkHttpClient) : AnnictClient