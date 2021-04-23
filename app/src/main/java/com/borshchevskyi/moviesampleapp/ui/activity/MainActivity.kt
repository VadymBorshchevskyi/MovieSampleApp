package com.borshchevskyi.moviesampleapp.ui.activity

import androidx.viewbinding.ViewBinding
import com.borshchevskyi.moviesampleapp.common.BaseActivity
import com.borshchevskyi.moviesampleapp.common.viewBinding
import com.borshchevskyi.moviesampleapp.databinding.ActivityMainBinding
import com.borshchevskyi.moviesampleapp.ui.viewModel.MainVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    override val binding: ViewBinding by viewBinding(ActivityMainBinding::inflate)
    override val viewModel: MainVM by viewModel()


    override fun initData() {
    }


    override fun initViews() {
    }

    override fun subscribeUI() {

    }

}