package com.featuresdk

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.view.ContextThemeWrapper
import com.featuresdk.dialog.DialogBuilderImpl
import com.featuresdk.sdkconfig.NetworkConfiguratorImpl
import com.simplefeature.SimpleFeatureSdk
import com.simplefeature.log.LogLevel
import java.util.*

class FeatureSdkApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    initSimpleFeatureSdk()
  }

  private fun initSimpleFeatureSdk() {
    val simpleFeatureSdk = SimpleFeatureSdk.builder()
      .with(this)
      .language("pl")
      .networkConfig(NetworkConfiguratorImpl())
      .languageContextProvider { handleContextChange(this) }
      .dialogBuilder { DialogBuilderImpl() }
      .logLevel(LogLevel.DEBUG)
      .build()

    SimpleFeatureSdk.instance = simpleFeatureSdk
  }

  private fun handleContextChange(context: Context): Context {
    val wrapper = ContextThemeWrapper(context, R.style.AppTheme)
    val configuration = Configuration()
    configuration.setLocale(Locale("pl"))
    wrapper.applyOverrideConfiguration(configuration)
    return wrapper
  }
}