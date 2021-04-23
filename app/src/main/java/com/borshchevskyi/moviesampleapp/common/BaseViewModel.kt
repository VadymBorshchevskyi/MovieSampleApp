package com.borshchevskyi.moviesampleapp.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


abstract class BaseViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var isActive = true
    private var _isLoading = MutableLiveData<Boolean>(false)

    /**
     * set param showPreloader to toggle indicator
     */
    protected fun <P> doOnBackground(showPreloader: Boolean = false, doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        try {
            if (showPreloader) _isLoading.postValue(true)
            doCoroutineWork(doOnAsyncBlock, viewModelScope, Dispatchers.IO) {
                if (showPreloader) _isLoading.postValue(false)
            }
        }catch (e : Exception) {
            if (showPreloader) _isLoading.postValue(false)
            e.printStackTrace()
        }
    }

    protected fun <P> doOnUI(stopLoading: Boolean = true, doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        doCoroutineWork(doOnAsyncBlock, viewModelScope, Dispatchers.Main){
        }
    }

    suspend fun withDelay(time: Long, block: () -> Unit) {
        delay(time)
        block.invoke()
    }

    protected open fun handleError(error: Any) {
        doOnUI {
            //TODO base error handling
        }
    }

    override fun onCleared() {
        super.onCleared()
        isActive = false
        viewModelJob.cancel()
    }

    private inline fun <P> doCoroutineWork(
        crossinline doOnAsyncBlock: suspend CoroutineScope.() -> P,
        coroutineScope: CoroutineScope,
        context: CoroutineContext,
        crossinline doOnCompleteblock: suspend () -> Unit
    ) {
        coroutineScope.launch {
            withContext(context) {
                doOnAsyncBlock.invoke(this)
                doOnCompleteblock.invoke()
            }
        }
    }

    open fun onStart() {}
    open fun onResume() {}
    open fun onPause() {}
    open fun onStop() {}

    open fun onDestroy() {
        onCleared()
    }

    fun closeScreen() {

    }

    val isLoading : LiveData<Boolean> get() = _isLoading

}