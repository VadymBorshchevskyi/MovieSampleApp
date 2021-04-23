package com.borshchevskyi.moviesampleapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.borshchevskyi.domaine.common.BaseError
import com.borshchevskyi.domaine.entity.Movie
import com.borshchevskyi.domaine.repository.MovieRepository
import com.borshchevskyi.moviesampleapp.common.BaseViewModel

class HomeVM(
        private val rep: MovieRepository
) : BaseViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()

    init {
        requestMovie()
    }

    private fun requestMovie() {
        doOnBackground(true) {
            rep.requestMovies().either({ it ->
                println("___Request failed")
                when (it) {
                    is BaseError.ServerError -> {
                        //process server error codes
                    }
                    is BaseError.NetworkError -> {
                        //process timeout etc
                    }
                }
            }) {
                println("___ Request is OK: $it")
                _movies.postValue(it?.results)
            }
        }
    }

    val movies : LiveData<List<Movie>> get() = _movies

}
