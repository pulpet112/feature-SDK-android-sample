package com.simplefeature

import android.content.Context
import android.view.View
import com.simplefeature.dialog.DialogBuilder
import com.simplefeature.log.LogLevel

data class SdkConfiguration(
    val context: Context,
    var language: String,
    val networkConfigurator: NetworkConfigurator,
    val languageContextProvider: Context.() -> Context,
    val dialogBuilder: () -> DialogBuilder,
    val uiComponentProvider: (Context) -> View,
    val logLevel: LogLevel = LogLevel.NONE
)