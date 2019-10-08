package com.simplefeature.data.network

import io.reactivex.Completable
import retrofit2.http.GET

internal interface SimpleFeatureApi {

    @GET("api")
    fun getSample(): Completable

}