package com.example.lecture8

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class BaseCommunication : Communication {
    private val liveData = MutableLiveData<BaseViewModel.State>()

    override fun showState(state: BaseViewModel.State) {
        liveData.value = state
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<BaseViewModel.State>) {
        liveData.observe(owner, observer)
    }

    override fun isState(type: Int): Boolean {
        return liveData.value?.isType(type) ?: false
    }
}

interface Communication {
    fun showState(state: BaseViewModel.State)
    fun observe(owner: LifecycleOwner, observer: Observer<BaseViewModel.State>)
    fun isState(type: Int): Boolean
}