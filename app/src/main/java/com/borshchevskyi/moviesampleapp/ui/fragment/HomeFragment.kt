package com.borshchevskyi.moviesampleapp.ui.fragment

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
//import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.borshchevskyi.moviesampleapp.R
import com.borshchevskyi.moviesampleapp.common.BaseFragment
import com.borshchevskyi.moviesampleapp.common.viewBinding
import com.borshchevskyi.moviesampleapp.databinding.FragmentHomeBinding
import com.borshchevskyi.moviesampleapp.ui.adapter.HomeAdapter
import com.borshchevskyi.moviesampleapp.ui.viewModel.HomeVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override val viewModel: HomeVM by viewModel()
    override val binding by viewBinding(FragmentHomeBinding::bind)

    private lateinit var itemsAdapter: HomeAdapter

    override fun initData() {
        //viewModel.requestMovie()
    }

    override fun initViews() {

        itemsAdapter = HomeAdapter {
            Toast.makeText(activity, it.title, Toast.LENGTH_LONG).show()
            setFragmentResult("requestKey", bundleOf("movieId" to it.id))
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }

        binding.movies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }
    }

    override fun subscribeUI() {
        observe(viewModel.movies, itemsAdapter::update)
    }

}