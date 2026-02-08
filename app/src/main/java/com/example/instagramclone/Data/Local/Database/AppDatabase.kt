package com.example.instagramclone.Data.Local.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.instagramclone.Data.Local.Dao.FeedDao
import com.example.instagramclone.Data.Local.Dao.ReelDao
import com.example.instagramclone.Data.Local.Entity.FeedEntity
import com.example.instagramclone.Data.Local.Entity.ReelEntity

@Database(entities = [FeedEntity::class, ReelEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun feedDao(): FeedDao
    abstract fun reelDao(): ReelDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "instagram_db"
//                ).build()
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "instagram_db"
                )
                    .fallbackToDestructiveMigration() // Add this line
                    .build()
            }
            return INSTANCE!!
        }
    }
}