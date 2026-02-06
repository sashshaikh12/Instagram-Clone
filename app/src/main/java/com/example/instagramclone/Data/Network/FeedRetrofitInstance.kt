package com.example.instagramclone.Data.Network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FeedRetrofitInstance {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val api: FeedApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dfbf9976-22e3-4bb2-ae02-286dfd0d7c42.mock.pstmn.io/")
            .addConverterFactory(MoshiConverterFactory.create(moshi)) // Pass moshi here
            .build()
            .create(FeedApiService::class.java)
    }
}