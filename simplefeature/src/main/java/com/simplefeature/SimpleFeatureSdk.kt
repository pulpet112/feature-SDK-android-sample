package com.simplefeature

import android.app.Application
import android.content.Context
import com.simplefeature.dialog.DialogBuilder
import com.simplefeature.log.LogLevel
import com.simplefeature.log.SdkLogger
import com.simplefeature.data.network.ApiBuilder

open class SimpleFeatureSdk {

  companion object {
    @JvmStatic
    lateinit var instance: SimpleFeatureSdk

    @JvmStatic
    fun builder() = BuilderContext()
  }

  lateinit var sdkConfiguration: SdkConfiguration

  private lateinit var simpleFeatureSdkService: SimpleFeatureSdkService

  class BuilderContext internal constructor() {
    fun with(appContext: Application): BuilderLanguage {
      return BuilderLanguage(appContext)
    }
  }

  class BuilderLanguage internal constructor(
    private val appContext: Application
  ) {
    fun language(language: String): BuilderNetworkConfig {
      return BuilderNetworkConfig(appContext, language)
    }
  }

  class BuilderNetworkConfig internal constructor(
    private val appContext: Application,
    private val language: String
  ) {
    fun networkConfig(networkConfigurator: NetworkConfigurator): BuilderLanguageContextProvider {
      return BuilderLanguageContextProvider(appContext, language, networkConfigurator)
    }
  }

  class BuilderLanguageContextProvider internal constructor(
    private val appContext: Application,
    private val language: String,
    private val networkConfigurator: NetworkConfigurator
  ) {
    fun languageContextProvider(languageContextProvider: Context.() -> Context): BuilderDialogBuilderProvider {
      return BuilderDialogBuilderProvider(appContext, language, networkConfigurator, languageContextProvider)
    }
  }

  class BuilderDialogBuilderProvider internal constructor(
    private val appContext: Application,
    private val language: String,
    private val networkConfigurator: NetworkConfigurator,
    private val languageContextProvider: Context.() -> Context
  ) {
    fun dialogBuilder(dialogBuilder: () -> DialogBuilder): Builder {
      return Builder(appContext, language, networkConfigurator, languageContextProvider, dialogBuilder)
    }
  }

  class Builder internal constructor(
    private val appContext: Application,
    private val language: String,
    private val networkConfigurator: NetworkConfigurator,
    private val languageContextProvider: Context.() -> Context,
    private val dialogBuilder: () -> DialogBuilder
  ) {
    private var logLevel: LogLevel = LogLevel.NONE

    fun logLevel(logLevel: LogLevel): Builder {
      this.logLevel = logLevel
      return this
    }
    fun build(): SimpleFeatureSdk {
      val simpleFeatureSdk = SimpleFeatureSdk()
      simpleFeatureSdk.sdkConfiguration = SdkConfiguration(
        appContext,
        language,
        networkConfigurator,
        languageContextProvider,
        dialogBuilder,
        logLevel)

      SdkLogger.setLogLevel(logLevel)

      simpleFeatureSdk.initService()

      instance = simpleFeatureSdk

      return simpleFeatureSdk
    }
  }

  private fun initService() {
    val apiBuilder = ApiBuilder(sdkConfiguration.networkConfigurator)
    simpleFeatureSdkService = SimpleFeatureSdkService(apiBuilder)
  }

}