package com.gustavolima.roomproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gustavolima.roomproject.database.daos.UserDao
import com.gustavolima.roomproject.database.models.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun UserDao(): UserDao
    companion object {

        private const val DATABASE_NAME: String = "user_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            ).build()
    }
}