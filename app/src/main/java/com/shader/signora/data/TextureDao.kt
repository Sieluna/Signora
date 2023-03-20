package com.shader.signora.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TextureDao {
    @Query("SELECT * FROM textures")
    fun getAll(): LiveData<List<Texture>>

    @Query("SELECT * from textures where textureId = :id")
    fun getById(id: Int): Texture?

    @Insert
    suspend fun insert(texture: Texture)
}