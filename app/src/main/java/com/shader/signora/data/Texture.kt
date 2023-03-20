package com.shader.signora.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("textures")
data class Texture(
    @PrimaryKey(true)
    val textureId: Long = 0,
    val name: String,
    val width: Int,
    val height: Int,
    val ratio: Float,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val payload: ByteArray
)