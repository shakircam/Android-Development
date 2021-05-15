package com.example.prioritynote.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.prioritynote.data.model.PriorityNote


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(priorityNote: PriorityNote)

    @Query("SELECT * FROM priority_note ORDER BY id ASC")
     fun getAllData() : LiveData<List<PriorityNote>>

    @Update
    suspend fun updateData(priorityNote: PriorityNote)

    @Delete
    suspend fun deleteItem(priorityNote: PriorityNote)

    @Query("DELETE FROM priority_note")
    suspend fun deleteAll()

     @Query("SELECT * FROM priority_note WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery:String) : LiveData<List<PriorityNote>>

    @Query("SELECT * FROM priority_note ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END ")
    fun sortByHighPriority():LiveData<List<PriorityNote>>

    @Query("SELECT * FROM priority_note ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END ")
    fun sortByLowPriority():LiveData<List<PriorityNote>>

}