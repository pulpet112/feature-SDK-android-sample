package com.simplefeature

import android.content.Context
import com.simplefeature.dialog.DialogBuilder
import com.simplefeature.log.LogLevel

data class SdkConfiguration(
    val context: Context,
    var language: String,
    val networkConfigurator: NetworkConfigurator,
    val languageContextProvider: Context.() -> Context,
    val dialogBuilder: () -> DialogBuilder,
    val logLevel: LogLevel = LogLevel.NONE
)