package com.simplefeature.dialog

import androidx.fragment.app.DialogFragment

interface DialogBuilder {

  fun setTitle(title: String): DialogBuilder

  fun setMessage(message: String): DialogBuilder

  fun setPositiveButtonText(buttonText: String): DialogBuilder

  fun setNegativeButtonText(buttonText: String): DialogBuilder

  fun build(): DialogFragment
}