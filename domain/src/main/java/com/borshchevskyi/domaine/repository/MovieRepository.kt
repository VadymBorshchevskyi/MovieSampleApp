package com.borshchevskyi.domaine.repository

import com.borshchevskyi.domaine.common.BaseError
import com.borshchevskyi.domaine.common.Either
import com.borshchevskyi.domaine.entity.Movie
import com.borshchevskyi.domaine.entity.PopularMovieRespond

interface MovieRepository {
    suspend fun requestMovies(): Either<BaseError, PopularMovieRespond?>
    suspend fun requestMovie(id: Int): Either<BaseError, Movie?>
}