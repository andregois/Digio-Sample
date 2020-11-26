package com.andregois.digioapp.data
import com.andregois.digioapp.domain.DigioResponse
import javax.inject.Singleton

@Singleton
interface DigioDataSource {
    suspend fun getDigioData(): DigioResponse
}