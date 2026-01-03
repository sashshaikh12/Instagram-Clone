package com.example.instagramclone.Data.Network

import com.example.instagramclone.Data.Network.Dto.FeedResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface FeedApiService {

    @GET("user/feed")
    suspend fun getPosts(): Response<FeedResponseDto>

}