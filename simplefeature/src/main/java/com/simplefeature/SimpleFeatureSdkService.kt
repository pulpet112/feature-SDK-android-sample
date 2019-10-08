package com.simplefeature

import com.simplefeature.data.RemoteDataSource
import com.simplefeature.data.SimpleFeatureRepository
import com.simplefeature.data.network.ApiBuilder

internal class SimpleFeatureSdkService(apiBuilder: ApiBuilder) {

    init {
        val remoteDataSource = RemoteDataSource(apiBuilder.getConfiguredApi())
        val repository = SimpleFeatureRepository(remoteDataSource)
    }

}