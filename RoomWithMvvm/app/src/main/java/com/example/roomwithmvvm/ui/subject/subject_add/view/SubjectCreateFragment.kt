package com.example.roomwithmvvm.ui.subject.subject_add.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomwithmvvm.data.repository.student.StudentRepositoryImpl
import com.example.roomwithmvvm.data.repository.student.Subject
import com.example.roomwithmvvm.databinding.FragmentSubjectCreateBinding
import com.example.roomwithmvvm.ui.subject.subject_add.viewmodel.SubjectCreateViewModel
import com.example.roomwithmvvm.ui.subject.subject_add.viewmodel.SubjectCreateViewModelFactory


class SubjectCreateFragment : Fragment() {

    private lateinit var binding : FragmentSubjectCreateBinding
    private val repository by lazy { StudentRepositoryImpl(requireContext().applicationContext) }

    private val viewModel by lazy {
        val factory = SubjectCreateViewModelFactory(repository)
        ViewModelProvider(this,factory).get(SubjectCreateViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentSubjectCreateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createSubButton.setOnClickListener {
            val subName = binding.subjectNameEditText.text.toString()
            val subCode = binding.subjectCodeEditText.text.toString()
            val subCredit = binding.subjectCreditEditText.text.toString()

            if (subName.isEmpty() || subCode.isEmpty()  || subCredit.isEmpty()){
                Toast.makeText(requireContext(),"All field are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val subject = Subject(

                    name = subName,
                    subjectCode = subCode.toInt(),
                    credit = subCredit.toInt()
            )
            //viewModel.createSubject(subject)
        }
    }
}