package com.digi.api_app.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// sample url
// 'https://books.googleapis.com/books/v1/volumes?q=warbreaker&maxResults=10&printType=BOOKS'


const val BASE_URL = "https://books.googleapis.com/books/v1"
const val QUERY_PARAM = "q"
const val MAX_RESULTS = "maxResults"
const val PRINT_TYPE = "printType"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
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
    ): String
}


object BookApi {
    val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }
}

