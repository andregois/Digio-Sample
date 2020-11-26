package com.andregois.digioapp.domain

import com.google.gson.annotations.SerializedName

data class DigioResponse(
    @SerializedName("cash")
    val cash: Cash,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("spotlight")
    val spotlight: List<Spotlight>
)





