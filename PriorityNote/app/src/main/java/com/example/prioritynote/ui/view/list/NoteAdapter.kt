package com.example.prioritynote.ui.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.prioritynote.R
import com.example.prioritynote.data.model.Priority
import com.example.prioritynote.data.model.PriorityNote


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

     var noteList = emptyList<PriorityNote>()

    class MyViewHolder(view :View):RecyclerView.ViewHolder(view){

        val mTitle = view.findViewById(R.id.title_txt) as TextView
        val mPriority = view.findViewById(R.id.priority_indicator) as CardView
        val mDescription = view.findViewById(R.id.description_txt) as TextView

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]

        holder.mTitle.text = currentItem.title
        holder.mDescription.text = currentItem.description

        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(noteList[position])
            holder.itemView.findNavController().navigate(action)
        }

        when(currentItem.priority){
            Priority.HIGH -> {holder.mPriority.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.red))}
            Priority.MEDIUM -> {holder.mPriority.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.yellow))}
            Priority.LOW ->  {holder.mPriority.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.green))}
        }

    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun setData(priorityNote: List<PriorityNote>){
        val priorityNoteDiffUtil = PriorityNoteDiffUtil(noteList,priorityNote)
        val priorityNoteDiffResult = DiffUtil.calculateDiff(priorityNoteDiffUtil)
        this.noteList = priorityNote
        priorityNoteDiffResult.dispatchUpdatesTo(this)
    }
}