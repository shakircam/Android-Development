package com.example.prioritynote.data.repository

import androidx.lifecycle.LiveData
import com.example.prioritynote.data.local.NoteDao
import com.example.prioritynote.data.model.PriorityNote

class PriorityNoteRepository(private val noteDao: NoteDao) {

    val getAllData : LiveData<List<PriorityNote>> = noteDao.getAllData()
    val  sortByHighPriority: LiveData<List<PriorityNote>> = noteDao.sortByHighPriority()
    val  sortByLowPriority: LiveData<List<PriorityNote>> = noteDao.sortByLowPriority()

    suspend fun insertNote(priorityNote: PriorityNote){
        noteDao.insertNote(priorityNote)
    }

    suspend fun updateData(priorityNote: PriorityNote){
        noteDao.updateData(priorityNote)
    }

    suspend fun deleteItem(priorityNote: PriorityNote){
        noteDao.deleteItem(priorityNote)
    }

    suspend fun deleteAll(){
        noteDao.deleteAll()
    }

    fun searchDatabase(searchQuery : String) : LiveData<List<PriorityNote>> {
        return noteDao.searchDatabase(searchQuery)
    }




}