package com.example.prioritynote.ui.view.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.prioritynote.R
import com.example.prioritynote.data.model.PriorityNote
import com.example.prioritynote.databinding.FragmentAddBinding
import com.example.prioritynote.databinding.FragmentUpdateBinding
import com.example.prioritynote.ui.viewmodel.PriorityNoteViewModel
import com.example.prioritynote.ui.viewmodel.SharedViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val priorityNoteViewModel : PriorityNoteViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        //safeArgs for dataShow in UpdateFragment
        binding.currentTitleEt.setText(args.currentItem.title)
        binding.currentDescriptionEt.setText(args.currentItem.description)
        binding.currentPrioritiesSpinner.setSelection(sharedViewModel.parsePriorityInt(args.currentItem.priority))

        binding.currentPrioritiesSpinner.onItemSelectedListener = sharedViewModel.listener

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_save ) {
            updateNoteData()
        }else if(item.itemId == R.id.menu_delete){
            removeItem()
        }
        return super.onOptionsItemSelected(item)
    }



    private fun updateNoteData(){

        val title = binding.currentTitleEt.text.toString()
        val description = binding.currentDescriptionEt.text.toString()
        val priority = binding.currentPrioritiesSpinner.selectedItem.toString()


        val validation = sharedViewModel.verifyData(title,description)
        if (validation){
            val updateData = PriorityNote(
                    args.currentItem.id,
                    title,
                    sharedViewModel.parsePriority(priority),
                    description
            )
            priorityNoteViewModel.updateData(updateData)
            Toast.makeText(requireContext(),"Data Update Successfully!", Toast.LENGTH_SHORT).show()

            //navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Data Update failed!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun removeItem() {
       val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            priorityNoteViewModel.deleteItem(args.currentItem)
            Toast.makeText(requireContext(),"Successfully Removed: ${args.currentItem.title}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){ _, _ ->}
        builder.setTitle("Delete ${args.currentItem.title}?")
        builder.setMessage("Are You Sure To Remove The Item ${args.currentItem.title}?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}