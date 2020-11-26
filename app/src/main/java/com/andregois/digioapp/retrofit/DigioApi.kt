package com.andregois.digioapp.retrofit

import com.andregois.digioapp.domain.DigioResponse
import retrofit2.http.GET

interface DigioApi {

    @GET("products")
    suspend fun getAllData() : DigioResponse
}