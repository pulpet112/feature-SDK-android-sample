package com.simplefeature.data

import com.simplefeature.data.network.SimpleFeatureApi

internal class RemoteDataSource(private val simpleFeatureApi: SimpleFeatureApi) {

    fun getSample() = simpleFeatureApi.getSample()

}