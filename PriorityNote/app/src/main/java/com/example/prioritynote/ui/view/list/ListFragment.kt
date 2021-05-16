package com.example.prioritynote.ui.view.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.prioritynote.R
import com.example.prioritynote.data.model.PriorityNote
import com.example.prioritynote.databinding.FragmentListBinding
import com.example.prioritynote.ui.viewmodel.PriorityNoteViewModel
import com.example.prioritynote.ui.viewmodel.SharedViewModel
import com.example.prioritynote.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class ListFragment : Fragment(),SearchView.OnQueryTextListener {

    private val priorityNoteViewModel : PriorityNoteViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()

    private var _binding : FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter: NoteAdapter by lazy { NoteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater,container,false)

        setHasOptionsMenu(true)
        //keyboard hiding..
         hideKeyboard(requireActivity())
        binding.floatingActionButton.setOnClickListener {
          findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }

        //setupRecyclerView
        initRecyclerView()

        //Observe LiveData
        priorityNoteViewModel.getAllData.observe(viewLifecycleOwner,{data ->
            adapter.setData(data)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu,menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId  ) {
             R.id.menu_delete_all -> deleteAllData()
             R.id.menu_priority_high -> priorityNoteViewModel.sortByHighPriority.observe(this, { adapter.setData(it) })
             R.id.menu_priority_low -> priorityNoteViewModel.sortByLowPriority.observe(this, { adapter.setData(it) })
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.itemAnimator = SlideInUpAnimator().apply { addDuration = 300 }
        swipeToDelete(recyclerView)

    }

    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deleteItem = adapter.noteList[viewHolder.adapterPosition]
                priorityNoteViewModel.deleteItem(deleteItem)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                Toast.makeText(requireContext(),"Successfully removed: '${deleteItem.title}'",Toast.LENGTH_SHORT).show()
                //Restored deletedItem
                getBackDeletedData(viewHolder.itemView,deleteItem)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

   private fun getBackDeletedData(view:View,deletedItem: PriorityNote){
        val snackBar = Snackbar.make(view,"Deleted '${deletedItem.title}'",Snackbar.LENGTH_LONG)
        snackBar.setAction("Undo"){
            priorityNoteViewModel.insertData(deletedItem)
        }
         snackBar.show()
    }

    private fun deleteAllData(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            priorityNoteViewModel.deleteAll()
            Toast.makeText(requireContext(),"Successfully Removed All Data", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){ _, _ ->}
        builder.setTitle("Delete Everything? ")
        builder.setMessage("Are You Sure To Remove All Item? ")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchItemInDatabase(query)
        }
        return true
    }

    private fun searchItemInDatabase(query: String) {
       var searchQuery = query
        searchQuery = "%$searchQuery%"
        priorityNoteViewModel.searchDatabase(searchQuery).observe(this, { list ->
            list?.let { adapter.setData(it) }
        })
    }


    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchItemInDatabase(query)
        }
        return true
    }
}