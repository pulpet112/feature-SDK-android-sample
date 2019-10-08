package com.featuresdk.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.featuresdk.R
import kotlinx.android.synthetic.main.fragment_main_app_dialog.view.*


class MainAppDialogFragment : DialogFragment() {

  companion object {
    private const val TITLE_EXTRA = "TITLE_EXTRA"
    private const val MESSAGE_EXTRA = "MESSAGE_EXTRA"
    private const val POSITIVE_BUTTON_TEXT_EXTRA = "POSITIVE_BUTTON_TEXT_EXTRA"
    private const val NEGATIVE_BUTTON_TEXT_EXTRA = "NEGATIVE_BUTTON_TEXT_EXTRA"

    fun newInstance(
      title: String,
      message: String,
      positiveButtonText: String,
      negativeButtonText: String
    ): MainAppDialogFragment {
      val bundle = Bundle()
      bundle.putString(TITLE_EXTRA, title)
      bundle.putString(MESSAGE_EXTRA, message)
      bundle.putString(POSITIVE_BUTTON_TEXT_EXTRA, positiveButtonText)
      bundle.putString(NEGATIVE_BUTTON_TEXT_EXTRA, negativeButtonText)
      val fragment = MainAppDialogFragment()
      fragment.arguments = bundle
      return fragment
    }
  }

  private var positiveClickListener: (() -> Unit)? = null
  private var negativeClickListener: (() -> Unit)? = null

  override fun onResume() {
    super.onResume()
    dialog?.window?.setLayout(
      ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT
    )
    dialog?.window?.setGravity(Gravity.CENTER)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val rootView = inflater.inflate(R.layout.fragment_main_app_dialog, container, false)

    rootView.title.text = arguments?.getString(TITLE_EXTRA)
    rootView.message.text = arguments?.getString(MESSAGE_EXTRA)
    rootView.positiveButton.text = arguments?.getString(POSITIVE_BUTTON_TEXT_EXTRA)
    rootView.negativeButton.text = arguments?.getString(NEGATIVE_BUTTON_TEXT_EXTRA)
    rootView.negativeButton.setOnClickListener {
      negativeClickListener?.invoke()
    }
    rootView.positiveButton.setOnClickListener {
      positiveClickListener?.invoke()
    }

    return rootView
  }

  fun setOnPositiveClickListener(action: () -> Unit) {
    this.positiveClickListener = action
  }

  fun setOnNegativeClickListener(action: () -> Unit) {
    this.negativeClickListener = action
  }
}