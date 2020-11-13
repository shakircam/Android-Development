package com.example.recyclerviewproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewproject.adapterClass.CollegeListAdapter
import com.example.recyclerviewproject.adapterClass.ItemListener
import com.example.recyclerviewproject.model.CollegeCallBack
import com.example.recyclerviewproject.model.CollegeData
import com.example.recyclerviewproject.model.CollegeModel
import com.example.recyclerviewproject.model.CollegeModelImp

class MainActivity : AppCompatActivity() {
    private lateinit var collegeModel: CollegeModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showCollegeList()
    }

    private fun showCollegeList(){

        collegeModel = CollegeModelImp()
         collegeModel.getCollegeDetails(object : CollegeCallBack {
            override fun onSuccess(collegeList: ArrayList<CollegeData>) {

                val adapter = CollegeListAdapter( collegeList, object : ItemListener {

                    override fun onEmailClicked(position: Int) {
                        val intent = Intent(Intent.ACTION_SENDTO)
                        intent.data = Uri.parse("mailto: shakircam@gmail.com")
                        intent.putExtra(Intent.EXTRA_EMAIL, "test")
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Good Evening")

                        startActivity(intent)
                    }

                    override fun onItemClicked(position: Int) {
                        Toast.makeText(this@MainActivity,"i will do this next day",Toast.LENGTH_SHORT).show()
                    }

                    override fun onNumberClicked(position: Int) {
                        val dialIntent = Intent(Intent.ACTION_DIAL)
                        dialIntent.data = Uri.parse("tel:" + "01917380832")
                        startActivity(dialIntent)
                    }

                })
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                recyclerView.adapter = adapter
            }

            override fun onError(errorMessage: Throwable) {
                TODO("Not yet implemented")
            }

        })






    }
}