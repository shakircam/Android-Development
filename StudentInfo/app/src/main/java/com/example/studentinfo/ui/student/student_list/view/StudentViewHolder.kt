package com.example.studentinfo.ui.student.student_list.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.studentinfo.databinding.ItemStudentBinding

class StudentViewHolder(binding:ItemStudentBinding):RecyclerView.ViewHolder(binding.root) {

    val nameTextView = binding.nameTextView
    val registrationNumTextView = binding.registrationNumTextView
    val emailTextView = binding.emailTextView
    val phoneTextView = binding.phoneTextView
    val btnEdit = binding.btnEdit
    val btnDelete = binding.btnDelete
}