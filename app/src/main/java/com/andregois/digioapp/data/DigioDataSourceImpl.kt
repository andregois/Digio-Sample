package com.andregois.digioapp.data

import com.andregois.digioapp.data.DigioDataSource
import com.andregois.digioapp.retrofit.DigioApi

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DigioDataSourceImpl @Inject constructor(
    private val digioApi: DigioApi
) : DigioDataSource {
    override suspend fun getDigioData()  = digioApi.getAllData()

}