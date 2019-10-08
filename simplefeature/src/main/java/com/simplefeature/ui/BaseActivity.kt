package com.simplefeature.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.simplefeature.SimpleFeatureSdk

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val languageProvider = SimpleFeatureSdk.instance.sdkConfiguration.languageContextProvider
        super.attachBaseContext(newBase.let(languageProvider))
    }
}