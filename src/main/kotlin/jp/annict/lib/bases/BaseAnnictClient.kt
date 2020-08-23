package jp.annict.lib.bases

import jp.annict.lib.interfaces.IAnnictClient
import okhttp3.OkHttpClient

abstract class BaseAnnictClient(val client: OkHttpClient) : IAnnictClient