package com.example.prioritynote.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.prioritynote.data.local.AppDatabase
import com.example.prioritynote.data.model.PriorityNote
import com.example.prioritynote.data.repository.PriorityNoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PriorityNoteViewModel(application: Application) : AndroidViewModel(application){

    private val noteDao = AppDatabase.getInstance(
        application
    ).noteDao()
    private val repository: PriorityNoteRepository = PriorityNoteRepository(noteDao)

    val getAllData: LiveData<List<PriorityNote>> = repository.getAllData
    val  sortByHighPriority: LiveData<List<PriorityNote>> = repository.sortByHighPriority
    val  sortByLowPriority: LiveData<List<PriorityNote>> = repository.sortByLowPriority

    fun insertData(priorityNote: PriorityNote){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(priorityNote)
        }
    }

    fun updateData(priorityNote: PriorityNote){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(priorityNote)
        }
    }

    fun deleteItem(priorityNote: PriorityNote){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(priorityNote)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery : String) : LiveData<List<PriorityNote>> {
        return repository.searchDatabase(searchQuery)
    }
}