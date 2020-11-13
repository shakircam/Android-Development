package com.example.recyclerviewproject.model

interface CollegeCallBack {
    fun onSuccess(collegeList: ArrayList<CollegeData>)
    fun onError(errorMessage: Throwable)
}