package com.simplefeature

import okhttp3.OkHttpClient

interface NetworkConfigurator {

  fun getBaseUrl(): String

  fun setPinning(builder: OkHttpClient.Builder)

  fun getTimeoutInSeconds(): Long
}
