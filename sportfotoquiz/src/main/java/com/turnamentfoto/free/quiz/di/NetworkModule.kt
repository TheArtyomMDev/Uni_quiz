package com.turnamentfoto.free.quiz.di

import org.koin.dsl.module
import com.turnamentfoto.free.quiz.data.datasource.APIService
import com.turnamentfoto.free.quiz.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var networkModule = module {
    fun provideRetrofit(): Retrofit {

        val interceptor2 = HttpLoggingInterceptor()

        interceptor2.level =
            HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .followRedirects(false)
            .followSslRedirects(false)
            .addInterceptor(interceptor2)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    fun provideAPIService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
    single {
        provideRetrofit()
    }
    single {
        provideAPIService(get())
    }
}

