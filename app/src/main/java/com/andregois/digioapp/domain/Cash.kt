package com.andregois.digioapp.domain

import com.google.gson.annotations.SerializedName

class Cash(
        @SerializedName("bannerURL")
        val bannerURL: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("title")
        val title: String
)
