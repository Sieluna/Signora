package com.shader.signora.io

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.*

@Entity("shaders")
data class Shader(@PrimaryKey(true) val shaderId: Long = 0, val name: String, val context: String, val created: Date, val modified: Date, val quality: Float)

@Entity("textures")
data class Texture(@PrimaryKey(true) val textureId: Long = 0, val name: String, val width: Int, val height: Int, val ratio: Float, @ColumnInfo(typeAffinity = ColumnInfo.BLOB) val payload: ByteArray)

@Entity(primaryKeys = ["shaderId", "textureId"])
data class ShaderTextureRef(val shaderId: Long, val textureId: Long)

data class ShaderTexture(
    @Embedded val shader: Shader,
    @Relation(
        parentColumn = "shaderId",
        entityColumn = "textureId",
        associateBy = Junction(ShaderTextureRef::class)
    )
    val textures: List<Texture>
)

@Dao
interface ShaderDao {
}

@Dao
interface TextureDao {

}

@Database(entities = [Shader::class, Texture::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shaderDao(): ShaderDao
    abstract fun textureDao(): TextureDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "signora")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // inject data to database

                    }
                })
                .build()
        }
    }
}
