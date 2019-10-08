package com.simplefeature.usecase

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.simplefeature.ui.InternalActivity

internal class OpenInternalScreenUseCase {

  fun execute(fragmentActivity: FragmentActivity) {
    fragmentActivity.startActivity(Intent(fragmentActivity, InternalActivity::class.java))
  }

}