package com.simplefeature.data.network

import com.simplefeature.NetworkConfigurator
import com.simplefeature.log.LogLevel
import com.simplefeature.log.SdkLogger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

internal class ApiBuilder(private val networkConfig: NetworkConfigurator) {

    fun getConfiguredApi(): SimpleFeatureApi {
        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(networkConfig.getTimeoutInSeconds(), TimeUnit.SECONDS)
            .readTimeout(networkConfig.getTimeoutInSeconds(), TimeUnit.SECONDS)

        networkConfig.setPinning(okHttpBuilder)

        if (SdkLogger.getLogLevel() > LogLevel.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(logging)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(networkConfig.getBaseUrl())
            .client(okHttpBuilder.build())
            .build()

        return retrofit.create(SimpleFeatureApi::class.java)
    }
}