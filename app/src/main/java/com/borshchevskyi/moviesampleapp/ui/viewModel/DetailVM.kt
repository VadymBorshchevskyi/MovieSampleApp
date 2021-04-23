package com.borshchevskyi.moviesampleapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.borshchevskyi.domaine.common.BaseError
import com.borshchevskyi.domaine.entity.Movie
import com.borshchevskyi.domaine.repository.MovieRepository
import com.borshchevskyi.moviesampleapp.common.BaseViewModel

class DetailVM(
        private val rep: MovieRepository
) : BaseViewModel(){
        private val _movie = MutableLiveData<Movie>()
        private var movieId = 0

        private fun requestMovie(id: Int) {
                doOnBackground(true) {
                        rep.requestMovie(id).either({ it ->
                                when (it) {
                                        is BaseError.ServerError -> {
                                                //process server error codes
                                        }
                                        is BaseError.NetworkError -> {
                                                //process timeout etc
                                        }
                                }
                        }) {
                                _movie.postValue(it)
                        }
                }
        }

        val movie : LiveData<Movie> get() = _movie

        fun setMovieId(id: Int){
                movieId = id
                requestMovie(movieId)
        }
}