package com.simplefeature.log

import android.util.Log

object SdkLogger {
  private var logLevel = LogLevel.INFO
  private const val TAG = "SimpleFeatureSDK"

  fun e(message: String) {
    if (shouldShowLog(LogLevel.ERROR)) {
      Log.e(TAG, message)
    }
  }

  fun e(message: String, throwable: Throwable?) {
    if (shouldShowLog(LogLevel.ERROR)) {
      Log.e(TAG, message, throwable)
    }
  }

  fun w(message: String) {
    if (shouldShowLog(LogLevel.WARN)) {
      Log.w(TAG, message)
    }
  }

  fun w(message: String, throwable: Throwable?) {
    if (shouldShowLog(LogLevel.WARN)) {
      Log.w(TAG, message, throwable)
    }
  }

  fun i(message: String) {
    if (shouldShowLog(LogLevel.INFO)) {
      Log.i(TAG, message)
    }
  }

  fun i(message: String, throwable: Throwable?) {
    if (shouldShowLog(LogLevel.INFO)) {
      Log.i(TAG, message, throwable)
    }
  }

  fun d(message: String) {
    if (shouldShowLog(LogLevel.DEBUG)) {
      Log.d(TAG, message)
    }
  }

  fun d(message: String, throwable: Throwable?) {
    if (shouldShowLog(LogLevel.DEBUG)) {
      Log.d(TAG, message, throwable)
    }
  }

  fun v(message: String) {
    if (shouldShowLog(LogLevel.VERBOSE)) {
      Log.v(TAG, message)
    }
  }

  fun v(message: String, throwable: Throwable?) {
    if (shouldShowLog(LogLevel.VERBOSE)) {
      Log.v(TAG, message, throwable)
    }
  }

  fun getLogLevel(): LogLevel {
    return logLevel
  }

  fun setLogLevel(logLevel: LogLevel) {
    SdkLogger.logLevel = logLevel
  }

  private fun shouldShowLog(logLevel: LogLevel): Boolean {
    return SdkLogger.logLevel >= logLevel
  }
}