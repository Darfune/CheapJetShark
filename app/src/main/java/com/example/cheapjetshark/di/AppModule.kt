package com.example.cheapjetshark.di

import com.example.cheapjetshark.network.CheapSharkApi
import com.example.cheapjetshark.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
class AppModule {

    //Retrofit
    @Provides
    @Singleton
    fun providesCheapSharkApi(): CheapSharkApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CheapSharkApi::class.java)

}