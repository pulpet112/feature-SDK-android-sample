package com.simplefeature.data

import io.reactivex.Completable

internal class SimpleFeatureRepository(private val remoteDataSource: RemoteDataSource) {

  fun getSample(): Completable {
    return remoteDataSource.getSample()
  }

}