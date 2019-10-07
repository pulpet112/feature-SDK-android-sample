package com.featuresdk.dialog

import androidx.fragment.app.DialogFragment
import com.simplefeature.dialog.DialogBuilder

class DialogBuilderImpl : DialogBuilder {

  private var title: String = ""
  private var message: String = ""
  private var positiveButtonText: String = ""
  private var negativeButtonText: String = ""

  override fun setTitle(title: String): DialogBuilder {
    this.title = title
    return this
  }

  override fun setMessage(message: String): DialogBuilder {
    this.message = message
    return this
  }

  override fun setPositiveButtonText(buttonText: String): DialogBuilder {
    this.positiveButtonText = buttonText
    return this
  }

  override fun setNegativeButtonText(buttonText: String): DialogBuilder {
    this.negativeButtonText = buttonText
    return this
  }

  override fun build(): DialogFragment {
    return MainAppDialogFragment.newInstance(title, message)
  }
}