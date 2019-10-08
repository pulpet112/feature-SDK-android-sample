package com.simplefeature.data

internal class SimpleFeatureRepository(private val remoteDataSource: RemoteDataSource) {

  fun getSample() {
    return remoteDataSource.getSample()
  }

}