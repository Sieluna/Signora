package com.shader.signora.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity("shaders")
data class Shader(
    @PrimaryKey(true)
    val shaderId: Long = 0,
    val name: String,
    val context: String,
    val created: Date,
    val modified: Date,
    val quality: Float,
    val textures: List<String>
)