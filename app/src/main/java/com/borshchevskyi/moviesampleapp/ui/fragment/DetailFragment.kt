package com.borshchevskyi.moviesampleapp.ui.fragment

import android.util.Log
import androidx.fragment.app.setFragmentResultListener
import com.borshchevskyi.domaine.entity.Movie
import com.borshchevskyi.moviesampleapp.R
import com.borshchevskyi.moviesampleapp.common.BaseFragment
import com.borshchevskyi.moviesampleapp.common.viewBinding
import com.borshchevskyi.moviesampleapp.databinding.FragmentDetailBinding
import com.borshchevskyi.moviesampleapp.ui.viewModel.DetailVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    override val viewModel: DetailVM by viewModel()
    override val binding by viewBinding(FragmentDetailBinding::bind)

    override fun initData() {

    }

    override fun initViews() {
        setFragmentResultListener("requestKey") { _, bundle ->
            viewModel.setMovieId(bundle.getInt("movieId"))
        }

    }

    override fun subscribeUI() {
        observe(viewModel.movie, this::changeUI)
    }

    private fun changeUI(movie: Movie){
        binding.title.text = movie.title
        binding.details.text = movie.overview
    }
}