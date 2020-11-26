package com.andregois.digioapp.data

import com.andregois.digioapp.data.DigioDataSource
import com.andregois.digioapp.domain.DigioResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DigioRepositoryImpl @Inject constructor(private val remoteDataSource: DigioDataSource): DigioRepository {

    override suspend fun getApiData(): DigioResponse {
        return remoteDataSource.getDigioData()
    }
}