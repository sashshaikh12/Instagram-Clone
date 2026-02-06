package com.example.instagramclone.Data.Network.Dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedResponseDto(
    // Ensure "posts" matches the key name in your Postman Mock JSON!
    @Json(name = "feed")
    val posts: List<FeedPostDto>
)