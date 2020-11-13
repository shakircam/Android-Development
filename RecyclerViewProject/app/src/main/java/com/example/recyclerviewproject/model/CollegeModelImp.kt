package com.example.recyclerviewproject.model

import com.example.recyclerviewproject.network.CollegeApiInterface
import com.example.recyclerviewproject.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollegeModelImp : CollegeModel {
//    override fun getCollegeList(): ArrayList<CollegeData> {
//        val collegeList = ArrayList<CollegeData>()
//
//        collegeList.add(CollegeData("Notre Deam ","Dhaka","Rahim","notredeam.edu.bd","01524685002","https://i.imgur.com/DkWRp2l.jpg"))
//        collegeList.add(CollegeData("Dhaka College","Dhaka","Karim","dhakacollege.edu.bd","01668500444","https://i.imgur.com/ACQSqRb.jpg"))
//        collegeList.add(CollegeData("Dhaka University","Dhaka","Arif","dhakauni.edu.bd","01568500000","https://i.imgur.com/c4xrIi0.jpg"))
//        collegeList.add(CollegeData("Comilla University","Comilla","Abul","comilla.edu.bd","01768504444","https://ibb.co/Qb5GjD9"))
//        collegeList.add(CollegeData("Chittagong University","Chittagong","Karim","chittagong.edu.bd","01978500355","https://ibb.co/Qb5GjD9"))
//        collegeList.add(CollegeData("Khulna University","Khulna","Jamal","khulna.edu.bd","01888500300","https://ibb.co/Qb5GjD9"))
//        collegeList.add(CollegeData("Rajshahi University","Rajshahi","Kalam","rajshahi.edu.bd","01666500311","https://ibb.co/Qb5GjD9"))
//        collegeList.add(CollegeData("Sylhet University","Sylhet","Mostafa","sylhet.edu.bd","01978500312","https://ibb.co/Qb5GjD9"))
//        collegeList.add(CollegeData("MC College","Sylhet","Jabbar","mcCollege.edu.bd","01765550312","https://ibb.co/Qb5GjD9"))
//        collegeList.add(CollegeData("Northen University","Dhaka","Fazlur","northen.edu.bd","01768500555","https://ibb.co/Qb5GjD9"))
//
//
//        return collegeList
//
//    }



    private val apiInterface = RetrofitClient.getClient().create(CollegeApiInterface::class.java)
    private val call = apiInterface.getCollegeDetails()

    override fun getCollegeDetails(collegeCallBack: CollegeCallBack) {
        call.enqueue(object : Callback<ArrayList<CollegeData>> {
            override fun onResponse(call: Call<ArrayList<CollegeData>>, response: Response<ArrayList<CollegeData>>) {
                response.body()?.let { collegeCallBack.onSuccess(it) }
            }

            override fun onFailure(call: Call<ArrayList<CollegeData>>, t: Throwable) {
                collegeCallBack.onError(t)
            }

        })
    }
}


