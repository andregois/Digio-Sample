package com.andregois.digioapp.data

import android.util.Log
import com.andregois.digioapp.domain.DigioResponse
import com.andregois.digioapp.utils.ApiResult
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DigioRepositoryImpl @Inject constructor(private val remoteDataSource: DigioDataSource): DigioRepository {

    override suspend fun getApiData():ApiResult<DigioResponse>{
        return try {
            ApiResult.success(remoteDataSource.getDigioData())
        }catch (exception:Exception){
            exception.printStackTrace()
            ApiResult.error(
                data = null,
                message = exception.message ?: "Error Occurred!",
            )
        }
    }

}