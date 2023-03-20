package com.shader.signora.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShaderDao {
    @Query("SELECT * FROM shaders")
    fun getAll(): LiveData<List<Shader>>

    @Query("SELECT * from shaders where shaderId = :id")
    fun getById(id: Int): Shader?

    @Insert
    suspend fun insert(shader: Shader)

    @Delete
    suspend fun delete(shader: Shader)
}