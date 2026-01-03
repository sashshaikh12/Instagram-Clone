package com.example.instagramclone.Data.Local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instagramclone.Data.Local.Entity.FeedEntity

@Dao
interface FeedDao {

    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<FeedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<FeedEntity>)

    @Query("UPDATE posts SET like_count = :count, liked_by_user = :liked WHERE post_id = :id")
    suspend fun updateLike(id: String, count: Int, liked: Boolean)
}