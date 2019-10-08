package com.simplefeature.schedulers

import io.reactivex.Scheduler

interface RxSchedulers {
  fun io(): Scheduler
  fun main(): Scheduler
}