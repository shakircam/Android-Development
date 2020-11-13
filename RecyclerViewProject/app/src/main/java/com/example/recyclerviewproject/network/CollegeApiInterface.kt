package com.example.recyclerviewproject.network

import com.example.recyclerviewproject.model.CollegeData
import retrofit2.Call
import retrofit2.http.GET

interface CollegeApiInterface {
    @GET("shakircam/CollegeList/master/app/src/main/java/com/example/collegelist/college_list_json")
    fun getCollegeDetails(): Call<ArrayList<CollegeData>>
}