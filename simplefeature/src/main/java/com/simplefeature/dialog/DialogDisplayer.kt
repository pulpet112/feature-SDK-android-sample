package com.simplefeature.dialog

import androidx.fragment.app.DialogFragment

interface DialogDisplayer {

  fun registerForPositiveButton(dialogTag: String, action: () -> Unit)

  fun registerForNegativeButton(dialogTag: String, action: () -> Unit)

  fun show(tag: String, dialogFragment: DialogFragment)
}
