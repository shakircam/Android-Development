package com.example.roomwithmvvm.ui.subject.subject_list.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.roomwithmvvm.R
import com.example.roomwithmvvm.databinding.FragmentSubjectListBinding
import kotlinx.android.synthetic.main.fragment_subject_list.*


class SubjectListFragment : Fragment() {

    private lateinit var binding : FragmentSubjectListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubjectListBinding.inflate(inflater,container,false)
        val view =   binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add_subject.setOnClickListener { findNavController().navigate(R.id.action_subjectListFragment_to_subjectCreateFragment) }
    }
}