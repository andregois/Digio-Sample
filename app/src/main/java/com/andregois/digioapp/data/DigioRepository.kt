package com.andregois.digioapp.data

import com.andregois.digioapp.data.DigioDataSource
import com.andregois.digioapp.domain.DigioResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DigioRepository @Inject constructor(private val remoteDataSource: DigioDataSource) {

    suspend fun getApiData(): DigioResponse {
        return remoteDataSource.getDigioData()
    }
}