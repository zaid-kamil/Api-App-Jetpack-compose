package com.digi.api_app.network

import com.digi.api_app.data.Volume
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// sample url
// 'https://books.googleapis.com/books/v1/volumes?q=warbreaker&maxResults=10&printType=BOOKS'


const val BASE_URL = "https://books.googleapis.com/books/v1/"
const val QUERY_PARAM = "q"
const val MAX_RESULTS = "maxResults"
const val PRINT_TYPE = "printType"


val moshi: Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BookApiService {
    @GET("volumes") // todo verify question mark presence
    suspend fun getBooks(
        @Query(QUERY_PARAM)
        query: String,
        @Query(MAX_RESULTS)
        results: String,
        @Query(PRINT_TYPE)
        type: String
    ): Volume
}


object BookApi {
    val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }
}

