package com.andregois.digioapp.domain

import com.google.gson.annotations.SerializedName

class Spotlight(
        @SerializedName("bannerURL")
        val bannerURL: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("name")
        val name: String = ""
)