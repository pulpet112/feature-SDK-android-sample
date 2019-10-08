package com.simplefeature.data.network

import retrofit2.http.GET

internal interface SimpleFeatureApi {

    @GET
    fun getSample()

}