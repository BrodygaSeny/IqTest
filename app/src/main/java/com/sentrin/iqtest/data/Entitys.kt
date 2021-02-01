package com.sentrin.iqtest.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class MainEntity constructor(
    @PrimaryKey
    var id:Int = 0, // for primary key - is nessesary
    var ScoreIq:Int,
    var name: String,
    var listAnsweredQuestions:MutableList<Int>,

    var darkTheme: Boolean
) : Parcelable


// for convert List<Int> in Entities
class IntListConverter {
    @TypeConverter
    fun fromString(intListString: String): MutableList<Int> {
        if (intListString=="") return mutableListOf()
        return intListString.split(",").map { it.toInt() } as MutableList<Int>
    }

    @TypeConverter
    fun toString(intList: MutableList<Int>): String {
        return intList.joinToString(separator = ",")
    }
}

