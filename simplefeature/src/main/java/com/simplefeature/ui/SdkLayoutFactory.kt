package com.simplefeature.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

class SdkLayoutFactory(private val delegate: AppCompatDelegate) : LayoutInflater.Factory2 {

  var mainAppView: View? = null

  override fun onCreateView(parent: View?, name: String?, context: Context, attrs: AttributeSet): View? {
    if (name == MainAppUiComponentView::class.java.name) {
      return mainAppView
    }

    return delegate.createView(parent, name, context, attrs)
  }

  override fun onCreateView(name: String?, context: Context, attrs: AttributeSet): View? {
    return onCreateView(null, name, context, attrs)
  }

}