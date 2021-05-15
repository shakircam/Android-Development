package com.example.prioritynote.ui.view.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.prioritynote.R
import com.example.prioritynote.data.model.Priority
import com.example.prioritynote.data.model.PriorityNote
import com.example.prioritynote.databinding.FragmentAddBinding
import com.example.prioritynote.ui.viewmodel.PriorityNoteViewModel
import com.example.prioritynote.ui.viewmodel.SharedViewModel


class AddFragment : Fragment() {
    private var _binding : FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val priorityNoteViewModel : PriorityNoteViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
         _binding = FragmentAddBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        binding.prioritiesSpinner.onItemSelectedListener = sharedViewModel.listener
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add)
            insertNoteData()
        return super.onOptionsItemSelected(item)
    }



    private fun insertNoteData(){

        val title = binding.titleEt.text.toString()
        val priority = binding.prioritiesSpinner.selectedItem.toString()
        val description = binding.descriptionEt.text.toString()

        val validation = sharedViewModel.verifyData(title,description)
        if (validation){
            val newData = PriorityNote(
                0,
                title,
                sharedViewModel.parsePriority(priority),
                description
            )
            priorityNoteViewModel.insertData(newData)
            Toast.makeText(requireContext(),"Data Stored to db Successfully!",Toast.LENGTH_SHORT).show()
            //navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Data Stored failed!",Toast.LENGTH_SHORT).show()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}