package com.simplefeature.ui

import android.os.Bundle
import android.view.LayoutInflater
import com.simplefeature.R
import com.simplefeature.SimpleFeatureSdk

internal class MainAppViewActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    val layoutFactory = SdkLayoutFactory(delegate)
    LayoutInflater.from(this).apply { factory2 = layoutFactory }
    layoutFactory.mainAppView = SimpleFeatureSdk.instance
      .sdkConfiguration
      .uiComponentProvider
      .invoke(this)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main_app_view)

  }
}