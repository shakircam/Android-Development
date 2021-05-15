package com.example.prioritynote.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.prioritynote.utils.TABLE_NOTE
import kotlinx.parcelize.Parcelize

@Entity(tableName = TABLE_NOTE)
@Parcelize
data class PriorityNote(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var priority : Priority,
    var description : String
):Parcelable
