package com.andregois.digioapp.data

import com.andregois.digioapp.domain.DigioResponse
import com.andregois.digioapp.utils.ApiResult
import javax.inject.Singleton

@Singleton
interface DigioRepository {
    suspend fun getApiData(): ApiResult<DigioResponse>
}