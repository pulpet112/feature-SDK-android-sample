package com.simplefeature.usecase

import android.widget.Toast
import com.simplefeature.data.SimpleFeatureRepository
import com.simplefeature.dialog.DialogBuilder
import com.simplefeature.dialog.DialogDisplayer
import com.simplefeature.dialog.ProgressSwitcher
import com.simplefeature.schedulers.RxSchedulers
import io.reactivex.disposables.Disposable

internal class ShowSampleUseCase(
  private val repository: SimpleFeatureRepository,
  private val rxSchedulers: RxSchedulers
) {

  companion object {
    const val ERROR_DIALOG_TAG = "ERROR_DIALOG_TAG"
    const val SUCCESS_DIALOG_TAG = "SUCCESS_DIALOG_TAG"
  }

  fun execute(
    progress: ProgressSwitcher,
    dialogDisplayer: DialogDisplayer,
    dialogBuilder: () -> DialogBuilder
  ): Disposable {
    return repository.getSample()
      .subscribeOn(rxSchedulers.io())
      .observeOn(rxSchedulers.main())
      .doOnSubscribe { progress.showProgress() }
      .doFinally { progress.dismissProgress() }
      .subscribe({
        val fragment = dialogBuilder.invoke()
          .setTitle("Success title")
          .setMessage("Success message")
          .setPositiveButtonText("OK")
          .setNegativeButtonText("CLOSE")
          .build()

        dialogDisplayer.show(SUCCESS_DIALOG_TAG, fragment)
        dialogDisplayer.registerForPositiveButton(SUCCESS_DIALOG_TAG) {
          Toast.makeText(fragment.requireContext(), "Positive button clicked", Toast.LENGTH_LONG).show()
        }
        dialogDisplayer.registerForNegativeButton(ERROR_DIALOG_TAG) { fragment.dismiss() }
      }, {
        val fragment = dialogBuilder.invoke()
          .setTitle("Error title")
          .setMessage("Error message")
          .setPositiveButtonText("OK")
          .setNegativeButtonText("CLOSE")
          .build()

        dialogDisplayer.show(ERROR_DIALOG_TAG, fragment)
        dialogDisplayer.registerForNegativeButton(ERROR_DIALOG_TAG) { fragment.dismiss() }
        dialogDisplayer.registerForPositiveButton(SUCCESS_DIALOG_TAG) { fragment.dismiss() }
      })
  }

}