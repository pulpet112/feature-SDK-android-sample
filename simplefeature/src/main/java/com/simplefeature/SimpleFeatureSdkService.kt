package com.simplefeature

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.simplefeature.data.RemoteDataSource
import com.simplefeature.data.SimpleFeatureRepository
import com.simplefeature.data.network.ApiBuilder
import com.simplefeature.dialog.DialogBuilder
import com.simplefeature.dialog.DialogDisplayer
import com.simplefeature.dialog.ProgressSwitcher
import com.simplefeature.schedulers.AndroidRxSchedulers
import com.simplefeature.usecase.OpenInternalScreenUseCase
import com.simplefeature.usecase.ShowSampleUseCase
import io.reactivex.disposables.Disposable

internal class SimpleFeatureSdkService(apiBuilder: ApiBuilder) {

  private val showSampleUseCase: ShowSampleUseCase
  private val openInternalScreenUseCase: OpenInternalScreenUseCase

  private val dialogBuilder: () -> DialogBuilder

  init {
    val remoteDataSource = RemoteDataSource(apiBuilder.getConfiguredApi())
    val repository = SimpleFeatureRepository(remoteDataSource)
    val schedulers = AndroidRxSchedulers()

    dialogBuilder = SimpleFeatureSdk.instance.sdkConfiguration.dialogBuilder

    showSampleUseCase = ShowSampleUseCase(repository, schedulers)
    openInternalScreenUseCase = OpenInternalScreenUseCase()
  }

  fun showSample(
    fragmentActivity: FragmentActivity,
    progressSwitcher: ProgressSwitcher,
    dialogDisplayer: DialogDisplayer
  ) {
    var disposable: Disposable? = null

    fragmentActivity.lifecycle.addObserver(object : LifecycleObserver {
      @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
      fun onDestroy() {
        disposable?.dispose()
      }
    })

    disposable = showSampleUseCase.execute(
      progressSwitcher,
      dialogDisplayer,
      dialogBuilder
    )
  }

  fun openInternalScreen(fragmentActivity: FragmentActivity) {
    openInternalScreenUseCase.execute(fragmentActivity)
  }
}