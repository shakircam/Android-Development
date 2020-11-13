package com.example.recyclerviewproject.model

interface CollegeModel {
   // fun getCollegeList():ArrayList<CollegeData>

    fun getCollegeDetails(collegeCallBack: CollegeCallBack)
}