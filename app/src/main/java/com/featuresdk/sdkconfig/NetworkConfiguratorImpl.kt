package com.featuresdk.sdkconfig

import com.simplefeature.NetworkConfigurator
import okhttp3.OkHttpClient

class NetworkConfiguratorImpl : NetworkConfigurator {
  override fun getTimeoutInSeconds(): Long {
    return 20L
  }

  override fun setPinning(builder: OkHttpClient.Builder) {
    // add pinning
  }

  override fun getBaseUrl(): String {
    return "https://baseUrl.pl/"
  }
}