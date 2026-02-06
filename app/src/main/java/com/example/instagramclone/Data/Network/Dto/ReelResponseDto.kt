package com.example.instagramclone.Data.Network.Dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReelResponseDto(
    // Ensure "reels" matches the key name in your Postman Mock JSON!
    @Json(name = "reels")
    val reels: List<ReelPostDto>
)