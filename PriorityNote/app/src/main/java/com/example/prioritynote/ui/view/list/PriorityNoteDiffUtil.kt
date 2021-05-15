package com.example.prioritynote.ui.view.list

import androidx.recyclerview.widget.DiffUtil
import com.example.prioritynote.data.model.PriorityNote

class PriorityNoteDiffUtil(
        private val oldList: List<PriorityNote>,
        private val newList: List<PriorityNote>
):DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && return oldList[oldItemPosition].title == newList[newItemPosition].title
                && return oldList[oldItemPosition].description == newList[newItemPosition].description
                && return oldList[oldItemPosition].priority == newList[newItemPosition].priority
    }
}