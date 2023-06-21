package com.codingblocks.myprojecttodoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoModel::class], version = 1)
abstract class ADatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao

    companion object {
        private var INSTANCE: ADatabase? = null
        fun getDatabase(context: Context): ADatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ADatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }
}
