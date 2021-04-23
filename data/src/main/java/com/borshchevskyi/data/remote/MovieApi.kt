package com.borshchevskyi.data.remote

import com.borshchevskyi.data.common.BaseResponse
import com.borshchevskyi.domaine.entity.Movie
import com.borshchevskyi.domaine.entity.PopularMovieRespond
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/popular?api_key=e4c86e1bbd085ef53c43479310bd6cbc")
    suspend fun getPopularFilm(): PopularMovieRespond //BaseResponse<PopularMovieRespond>

    @GET("movie/{id}?api_key=e4c86e1bbd085ef53c43479310bd6cbc")
    suspend fun getFilm(@Path("id") id: Int): Movie
}