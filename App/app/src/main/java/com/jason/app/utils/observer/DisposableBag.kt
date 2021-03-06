package com.jason.app.utils.observer

import com.jason.app.extensions.logError
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DisposableBag {
    var disposableBag: CompositeDisposable? = null

    init {
        disposableBag = CompositeDisposable()
    }

    fun add(disposable: Disposable) {
        if (disposableBag != null) {
            disposableBag?.add(disposable)
        } else {
            throw NotImplementedError("must bind AutoDisposable to a Lifecycle first")
        }
    }

    fun onStop() {
        disposableBag?.dispose()
        this.javaClass.simpleName.logError("Cancel this task success----->")
    }
}

fun Disposable.addTo(disposableBag: DisposableBag) {
    disposableBag.add(this)
}