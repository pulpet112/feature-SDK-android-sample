package com.simplefeature.data

import com.simplefeature.data.network.SimpleFeatureApi
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

internal class RemoteDataSource(private val simpleFeatureApi: SimpleFeatureApi) {

    fun getSample(): Completable {
        // mock response, should be replaced with simpleFeatureApi.getSample()
        return Completable.complete().delay(2000, TimeUnit.MILLISECONDS)
    }

}