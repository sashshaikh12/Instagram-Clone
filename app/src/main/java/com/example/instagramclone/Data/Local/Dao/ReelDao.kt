package com.example.instagramclone.Data.Local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.Data.Local.Entity.ReelEntity

@Dao
interface ReelDao {

    @Query("SELECT * FROM reels")
    suspend fun getAllReels(): List<ReelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReels(reels: List<ReelEntity>)

    @Query("UPDATE reels SET like_count = :count, liked_by_user = :liked WHERE reel_id = :id")
    suspend fun updateLike(id: String, count: Int, liked: Boolean)
}