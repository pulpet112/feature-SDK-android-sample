package com.featuresdk

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class SimpleView : LinearLayout {

  constructor(context: Context) : this(context, null)
  constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    View.inflate(context, R.layout.view_simple, this)
  }

}