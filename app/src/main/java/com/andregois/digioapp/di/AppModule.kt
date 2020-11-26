package com.andregois.digioapp.di

import com.andregois.digioapp.data.DigioDataSource
import com.andregois.digioapp.data.DigioDataSourceImpl
import com.andregois.digioapp.data.DigioRepository
import com.andregois.digioapp.data.DigioRepositoryImpl
import com.andregois.digioapp.retrofit.DigioApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = "https://7hgi9vtkdc.execute-api.sa-east-1.amazonaws.com/sandbox/"

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideInterceptor()= HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) =
        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, baseURL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideDigioService(retrofit: Retrofit) =
        retrofit.create(DigioApi::class.java)

    @Singleton
    @Provides
    fun provideDigioDataSource(digioApi: DigioDataSourceImpl): DigioDataSource = digioApi

    @Singleton
    @Provides
    fun provideDigioRepository(digioRepository: DigioRepositoryImpl): DigioRepository = digioRepository


}

