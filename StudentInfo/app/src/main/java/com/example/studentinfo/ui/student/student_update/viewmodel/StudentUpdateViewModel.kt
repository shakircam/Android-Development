package com.example.studentinfo.ui.student.student_update.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentinfo.core.DataFetchCallback
import com.example.studentinfo.data.repository.student.Student
import com.example.studentinfo.data.repository.student.StudentRepository
import kotlinx.coroutines.launch

class StudentUpdateViewModel(private val repository: StudentRepository) : ViewModel() {
    val studentUpdateSuccessLiveData = MutableLiveData<Unit>()
    val studentUpdateFailedLiveData = MutableLiveData<String>()


    fun updateStudent(student: Student){
        viewModelScope.launch {
            repository.updateStudent(student)
            studentUpdateSuccessLiveData.postValue(Unit)
        }
    }


}