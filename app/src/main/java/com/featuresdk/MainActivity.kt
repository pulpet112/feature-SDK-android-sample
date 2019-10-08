package com.featuresdk

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.featuresdk.dialog.MainAppDialogFragment
import com.simplefeature.SimpleFeatureSdk
import com.simplefeature.dialog.DialogDisplayer
import com.simplefeature.dialog.ProgressSwitcher
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setListeners()
  }

  private fun setListeners() {
    executeButton.setOnClickListener {
      SimpleFeatureSdk.instance.showSample(
        this,
        getProgressSwitcher(),
        getDialogDisplayer()
      )
    }

    openInternalScreenButton.setOnClickListener {
      SimpleFeatureSdk.instance.openInternalScreen(this)
    }
  }

  private fun getProgressSwitcher(): ProgressSwitcher {
    return object : ProgressSwitcher {
      override fun showProgress() {
        progressBar.visibility = View.VISIBLE
      }

      override fun dismissProgress() {
        progressBar.visibility = View.INVISIBLE
      }
    }
  }

  private fun getDialogDisplayer(): DialogDisplayer {
    return object : DialogDisplayer {
      var infoDialogFragment: WeakReference<MainAppDialogFragment>? = null

      override fun registerForPositiveButton(dialogTag: String, action: () -> Unit) {
        infoDialogFragment?.get()?.setOnPositiveClickListener(action)
      }

      override fun registerForNegativeButton(dialogTag: String, action: () -> Unit) {
        infoDialogFragment?.get()?.setOnNegativeClickListener(action)
      }

      override fun show(tag: String, dialogFragment: DialogFragment) {
        infoDialogFragment = WeakReference(dialogFragment as MainAppDialogFragment)
        dialogFragment.show(supportFragmentManager, tag)
      }
    }
  }
}
