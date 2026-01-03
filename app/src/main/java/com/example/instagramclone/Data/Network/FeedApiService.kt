package com.example.instagramclone.Data.Network

import com.example.instagramclone.Data.Network.Dto.FeedResponseDto
import com.example.instagramclone.Data.Network.Request.LikeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface FeedApiService {

    @GET("user/feed")
    suspend fun getPosts(): Response<FeedResponseDto>

    @POST("user/like")
    suspend fun likePost(@Body body: LikeRequest)

    @HTTP(method = "DELETE", path = "user/dislike", hasBody = true)
    suspend fun dislikePost(@Body body: LikeRequest)


}