package com.borshchevskyi.domaine.entity

data class PopularMovieRespond(
        val page: Int,
        val results: List<Movie>
)