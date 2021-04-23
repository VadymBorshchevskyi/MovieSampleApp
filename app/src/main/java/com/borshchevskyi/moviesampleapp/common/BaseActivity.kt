package com.borshchevskyi.moviesampleapp.common


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity : AppCompatActivity(), ViewInitializer {
    protected abstract val binding: ViewBinding
    protected abstract val viewModel: BaseViewModel

    protected var dp = Float.MAX_VALUE


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        initDpValue()
        subscribeUI()
    }


    override fun initDpValue(){
        dp = resources.displayMetrics.density
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
    }
}