package com.example.instagramclone.Data.Network

import com.example.instagramclone.Data.Network.Dto.FeedResponseDto
import com.example.instagramclone.Data.Network.Dto.ReelResponseDto
import com.example.instagramclone.Data.Network.Request.LikeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface ReelApiService {

    @GET("user/reels")
    suspend fun getReels(): Response<ReelResponseDto>

    @POST("user/like")
    suspend fun likeReel(@Body body: LikeRequestReel)

    @HTTP(method = "DELETE", path = "user/dislike", hasBody = true)
    suspend fun dislikeReel(@Body body: LikeRequestReel)


}