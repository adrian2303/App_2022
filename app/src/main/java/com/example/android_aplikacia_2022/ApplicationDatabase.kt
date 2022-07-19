package com.example.android_aplikacia_2022

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User :: class], version = 1)
abstract class ApplicationDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{

        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase{

            val tempInstance = INSTANCE
            if (tempInstance != null){

                return tempInstance
            }
            synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java, "application_database").build()
                    INSTANCE = instance
                    return instance
            }
        }
    }
}