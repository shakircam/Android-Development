package com.example.recyclerviewproject.adapterClass

interface ItemListener {
    fun onEmailClicked(position: Int )
    fun onItemClicked(position: Int)
    fun onNumberClicked(position: Int)
}