package com.andregois.digioapp.domain

import com.google.gson.annotations.SerializedName

class Product(
        @SerializedName("description")
        val description: String,
        @SerializedName("imageURL")
        val imageURL: String,
        @SerializedName("name")
        val name: String
)