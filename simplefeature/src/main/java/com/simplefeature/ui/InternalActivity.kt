package com.simplefeature.ui

import android.content.Intent
import android.os.Bundle
import com.simplefeature.R
import kotlinx.android.synthetic.main.activity_internal.*

class InternalActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_internal)

    firstButton.setOnClickListener {
      startActivity(Intent(this, MainAppViewActivity::class.java))
    }
  }

}