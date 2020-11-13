package com.example.recyclerviewproject.model

import java.io.Serializable

data class CollegeData(
                       val collegeName : String,
                       val district : String,
                       val chief : String,
                       val email : String,
                       val phone : String,
                       val imageUrl: String
) : Serializable
