package com.example.recyclerviewproject.adapterClass

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewproject.R
import com.example.recyclerviewproject.model.CollegeData
import java.time.LocalDateTime
import java.time.LocalDateTime.*
import java.time.format.DateTimeFormatter

class CollegeListAdapter(
        private val collegeList: ArrayList<CollegeData>,
        private val itemListener: ItemListener):
        RecyclerView.Adapter<CollegeListAdapter.CollegeViewHolder>() {

        private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollegeViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_college,parent,false)
        return CollegeViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CollegeViewHolder, position: Int) {
        val college = collegeList[position]

        Glide.with(context)
                .load(college.imageUrl)
                .placeholder(R.drawable.notredamecollege)
                .into(holder.ivCollege)

        holder.tvCollegeName.text = college.collegeName
        holder.tvDistrictName.text = "District: "+college.district
        holder.tvChiefName.text = "Chief: "+college.chief
        holder.tvEmail.text = college.email
        holder.tvPhone.text = college.phone


        holder.itemView.setOnClickListener{
            itemListener.onItemClicked(position)
        }

        holder.tvEmail.setOnClickListener{
            itemListener.onEmailClicked(position)
        }

        holder.tvPhone.setOnClickListener{
            itemListener.onNumberClicked(position)
        }

    }

    override fun getItemCount(): Int {
        return collegeList.size
    }

    class CollegeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivCollege = itemView.findViewById(R.id.iv_college) as AppCompatImageView
        val tvCollegeName = itemView.findViewById(R.id.tv_collegeName) as AppCompatTextView
        val tvDistrictName = itemView.findViewById(R.id.tv_districtName) as AppCompatTextView
        val tvChiefName = itemView.findViewById(R.id.tv_chiefName) as AppCompatTextView
        val tvEmail = itemView.findViewById(R.id.tv_email) as AppCompatTextView
        val tvPhone = itemView.findViewById(R.id.tv_phone) as AppCompatTextView
       


    }
}