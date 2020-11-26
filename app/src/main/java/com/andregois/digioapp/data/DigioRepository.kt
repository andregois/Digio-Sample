package com.andregois.digioapp.data

import com.andregois.digioapp.data.DigioDataSource
import com.andregois.digioapp.domain.DigioResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface DigioRepository {
    suspend fun getApiData(): DigioResponse
}