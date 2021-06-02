package com.kaverikr.nationalflag.nationalflag.models

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val totalResults: String?,
    @SerializedName("flagPNG")
    val status: String?,
    @SerializedName("capital")
    val articles: List<Articles>


)


data class Articles(

    val publishedAt: String?,
    val author: String?,
    val urlToImage: String?,
    val description: String?,
    val title: String?

)

