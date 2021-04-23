package com.borshchevskyi.domaine.entity

data class Movie (
        val id: Int,
        val adult: Boolean,
        val backdrop_path: String,
        val title: String,
        val overview: String
        )