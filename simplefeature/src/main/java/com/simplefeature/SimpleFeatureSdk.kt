package com.simplefeature

import android.app.Application
import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.simplefeature.data.network.ApiBuilder
import com.simplefeature.dialog.DialogBuilder
import com.simplefeature.dialog.DialogDisplayer
import com.simplefeature.dialog.ProgressSwitcher
import com.simplefeature.log.LogLevel
import com.simplefeature.log.SdkLogger

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
      return BuilderDialogBuilderProvider(
        appContext,
        language,
        networkConfigurator,
        languageContextProvider
      )
    }
  }

  class BuilderDialogBuilderProvider internal constructor(
    private val appContext: Application,
    private val language: String,
    private val networkConfigurator: NetworkConfigurator,
    private val languageContextProvider: Context.() -> Context
  ) {
    fun dialogBuilder(dialogBuilder: () -> DialogBuilder): BuilderUiComponent {
      return BuilderUiComponent(
        appContext,
        language,
        networkConfigurator,
        languageContextProvider,
        dialogBuilder
      )
    }
  }

  class BuilderUiComponent internal constructor(
    private val appContext: Application,
    private val language: String,
    private val networkConfigurator: NetworkConfigurator,
    private val languageContextProvider: Context.() -> Context,
    private val dialogBuilder: () -> DialogBuilder
  ) {
    fun uiComponentProvider(provider: (Context) -> View): Builder {
      return Builder(
        appContext,
        language,
        networkConfigurator,
        languageContextProvider,
        dialogBuilder,
        provider)
    }
  }

  class Builder internal constructor(
    private val appContext: Application,
    private val language: String,
    private val networkConfigurator: NetworkConfigurator,
    private val languageContextProvider: Context.() -> Context,
    private val dialogBuilder: () -> DialogBuilder,
    private val provider: (Context) -> View
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
        provider,
        logLevel
      )

      SdkLogger.setLogLevel(logLevel)

      instance = simpleFeatureSdk

      simpleFeatureSdk.initService()

      return simpleFeatureSdk
    }
  }

  private fun initService() {
    val apiBuilder = ApiBuilder(sdkConfiguration.networkConfigurator)
    simpleFeatureSdkService = SimpleFeatureSdkService(apiBuilder)
  }

  fun showSample(
    fragmentActivity: FragmentActivity,
    progressSwitcher: ProgressSwitcher,
    dialogDisplayer: DialogDisplayer
  ) {
    return simpleFeatureSdkService.showSample(
      fragmentActivity,
      progressSwitcher,
      dialogDisplayer
    )
  }

  fun openInternalScreen(fragmentActivity: FragmentActivity) {
    simpleFeatureSdkService.openInternalScreen(fragmentActivity)
  }
}