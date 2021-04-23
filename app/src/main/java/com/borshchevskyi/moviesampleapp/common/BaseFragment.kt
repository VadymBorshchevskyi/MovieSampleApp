package com.borshchevskyi.moviesampleapp.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.borshchevskyi.moviesampleapp.R
import java.lang.Exception


abstract class BaseFragment(@LayoutRes id : Int): Fragment(id),
        ViewInitializer {

    protected abstract val viewModel : BaseViewModel
    protected abstract val binding: ViewBinding

    private var dp = 1f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initViews()
        initDpValue()
        subscribeUI()
        observe(viewModel.isLoading, ::showLoading)
    }

    inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline function: (T) -> Unit) {
        liveData.observe(viewLifecycleOwner, Observer { it?.let { function.invoke(it)}})
    }

    inline fun <T> Fragment.observeNullable(liveData: LiveData<T>, crossinline function: (T) -> Unit) {
        liveData.observe(viewLifecycleOwner, Observer { function.invoke(it)})
    }

    override fun initDpValue(){
        dp = resources.displayMetrics.density
    }

    open fun popBackStack() {
        findNavController().popBackStack()
    }

    open fun showLoading(isShow: Boolean){
        binding.root.findViewById<View>(R.id.view_progress)?.let {
            it.visibility = if (isShow) View.VISIBLE else View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

}