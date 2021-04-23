package com.borshchevskyi.data.server

import com.borshchevskyi.data.remote.MovieApi
import com.borshchevskyi.domaine.common.BaseError
import com.borshchevskyi.domaine.common.Either
import com.borshchevskyi.domaine.common.proceedResponse
import com.borshchevskyi.domaine.entity.Movie
import com.borshchevskyi.domaine.entity.PopularMovieRespond
import com.borshchevskyi.domaine.repository.MovieRepository

class MovieServer(private val movieApi : MovieApi):
        MovieRepository {

    override suspend fun requestMovies(): Either<BaseError, PopularMovieRespond?> {
        return proceedResponse {
            val respondMovie = movieApi.getPopularFilm()
            Either.Success(respondMovie)
        }
    }

    override suspend fun requestMovie(id: Int): Either<BaseError, Movie?> {
        return proceedResponse {
            val respondMovie = movieApi.getFilm(id)
            Either.Success(respondMovie)
        }
    }
}