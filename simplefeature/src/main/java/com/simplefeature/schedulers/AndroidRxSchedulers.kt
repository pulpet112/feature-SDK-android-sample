package com.simplefeature.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidRxSchedulers : RxSchedulers {
  override fun main(): Scheduler {
    return AndroidSchedulers.mainThread()
  }

  override fun io(): Scheduler {
    return Schedulers.io()
  }
}