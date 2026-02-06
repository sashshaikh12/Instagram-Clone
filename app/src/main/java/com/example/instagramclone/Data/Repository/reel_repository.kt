package com.example.instagramclone.Data.Repository

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.annotation.RequiresPermission
import com.example.instagramclone.Data.Local.Dao.FeedDao
import com.example.instagramclone.Data.Local.Dao.ReelDao
import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.Data.Local.Entity.ReelEntity
import com.example.instagramclone.Data.Network.FeedApiService
import com.example.instagramclone.Data.Network.LikeRequestReel
import com.example.instagramclone.Data.Network.ReelApiService
import com.example.instagramclone.Data.Network.Request.LikeRequest
import com.example.instagramclone.Ui.FeedResult
import com.example.instagramclone.Ui.ReelResult
import com.example.instagramclone.Utils.NetworkUtils

class ReelRepository(
    private val api: ReelApiService,
    private val dao: ReelDao,
    private val context: Context
) {

    val TAG = "ERROR"
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    suspend fun getReel(): ReelResult {

        val isOnline = NetworkUtils.isNetworkAvailable(context)
        if (isOnline) {
            try {
                val response = api.getReels()

                // Map DTO → Entity
                val dtoList = response.body()?.reels ?: emptyList()


                val entities = dtoList.map { dto ->
                    ReelEntity(
                        reel_id = dto.reel_id,
                        user_name = dto.user_name,
                        user_image = dto.user_image,
                        reel_video= dto.reel_video,
                        like_count = dto.like_count,
                        liked_by_user = dto.liked_by_user
                    )
                }

                // Save to Room
                dao.insertReels(entities)

            } catch (e: Exception) {
                Log.d(TAG, "error = $e")
            }
        }

        // Always return Room data as it is the source of truth
        return ReelResult(
            reels = dao.getAllReels(),
            isOffline = !isOnline
        )
    }

    suspend fun toggleLike(reel: ReelEntity): Boolean {
        return try {
            if (reel.liked_by_user) {
                api.likeReel(
                    LikeRequestReel(
                        like = true,
                        reel_id = reel.reel_id
                    )
                )

            } else {
                api.dislikeReel(
                    LikeRequestReel(
                        like = false,
                        reel_id = reel.reel_id
                    )
                )

            }

            dao.updateLike(
                id = reel.reel_id,
                count = reel.like_count,
                liked = reel.liked_by_user
            )

            true
        } catch (e: Exception) {
            false
        }
    }


}
