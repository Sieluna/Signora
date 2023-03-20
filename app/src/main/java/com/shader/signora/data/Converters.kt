package com.shader.signora.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    @TypeConverter
    fun packStringArray(listOfString: String?): List<String?>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun unpackStringArray(listOfString: List<String?>?): String? {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun packDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun unpackDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }
}