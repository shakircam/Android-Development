package com.example.studentinfo.ui.student.student_update.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.studentinfo.R
import com.example.studentinfo.data.local.StudentDataSetChangeListener
import com.example.studentinfo.data.repository.student.Student
import com.example.studentinfo.data.repository.student.StudentRepositoryImpl
import com.example.studentinfo.databinding.FragmentStudentUpdateDialogBinding
import com.example.studentinfo.ui.student.student_update.viewmodel.StudentUpdateViewModel
import com.example.studentinfo.ui.student.student_update.viewmodel.StudentUpdateViewModelFactory
import com.example.studentinfo.utils.UtilExtensions.setTextEditable


class StudentUpdateDialogFragment : DialogFragment() {

    private var binding : FragmentStudentUpdateDialogBinding? = null
    private val repository by lazy { StudentRepositoryImpl(requireContext().applicationContext) }
    private var student: Student? = null

    private val viewModel by lazy {
        val factory = StudentUpdateViewModelFactory(repository)
        ViewModelProvider(this, factory).get(StudentUpdateViewModel::class.java)
    }
    private lateinit var studentDataSetChangeListener: StudentDataSetChangeListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is StudentDataSetChangeListener){
            studentDataSetChangeListener = context
        } else
            throw ClassCastException("Caller class must implement StudentCrudListener interface")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentUpdateDialogBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setTitle(getString(R.string.title_update_student))

        if (student!= null){
            binding?.studentNameEditText?.setTextEditable(student?.name ?: "")
            binding?.registrationEditText?.setTextEditable((student?.registration?: "") as String)
            binding?.phoneEditText?.setTextEditable(student?.phone?: "")
            binding?.emailEditText?.setTextEditable(student?.email?: "")

        }

        binding?.updateButton?.setOnClickListener {
            val id = if (student != null) student?.id else null
            val name = binding?.studentNameEditText?.text.toString()
            val registrationNumber = binding?.registrationEditText?.text.toString()
            val phone = binding?.phoneEditText?.text.toString()
            val email = binding?.emailEditText?.text.toString()

            val student = id?.let { it1 ->
                Student(
                    id = it1,
                    name = name,
                    registration = registrationNumber.toLong(),
                    phone = phone,
                    email = email)
            }
            if (id != null) {

                    if (student != null) {
                        viewModel.updateStudent(student).also {
                            dialog?.setCanceledOnTouchOutside(true);
                        }
                    }
            }
        }
        binding?.cancelButton?.setOnClickListener { dismiss() }

        viewModel.studentUpdateSuccessLiveData.observe(viewLifecycleOwner, {
            studentDataSetChangeListener.onStudentDataChanged()
            dismiss()
        })

        viewModel.studentUpdateFailedLiveData.observe(viewLifecycleOwner, {
            studentDataSetChangeListener.onStudentDataSetChangeError(it)
        })
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT

        dialog?.window?.setLayout(width, height)
    }

}



