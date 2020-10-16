package com.example.restaurant.services

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException

object ServiceBuilder {
    private var retrofit:Retrofit?=null
    private var okHttp =OkHttpClient.Builder()


    private fun getClientScalar(baseUrl:String="https://developers.zomato.com/api/v2.1/"):Retrofit{
        okHttp.addInterceptor { chain ->
            val request: Request =
                chain.request().newBuilder().addHeader("user-key", "78012f851bf81e7f38ae48899625d8d0").build()
            chain.proceed(request)
        }
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttp.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        return retrofit as Retrofit
    }

    fun<T> buildServiceScalar(contract: Class<T>):T{
        return getClientScalar().create(contract)
    }
}