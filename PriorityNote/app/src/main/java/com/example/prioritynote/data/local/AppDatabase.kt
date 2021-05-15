package com.example.prioritynote.data.local

import android.content.Context
import androidx.room.*
import com.example.prioritynote.data.model.Converter
import com.example.prioritynote.data.model.PriorityNote
import com.example.prioritynote.utils.DATABASE_NAME
import com.example.prioritynote.utils.DATABASE_VERSION
import androidx.room.TypeConverters

@TypeConverters(Converter::class)
@Database(entities = [PriorityNote::class],version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object DatabaseBuilder{
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}