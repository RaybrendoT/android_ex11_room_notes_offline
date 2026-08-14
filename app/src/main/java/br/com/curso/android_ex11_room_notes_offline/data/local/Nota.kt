package br.com.curso.android_ex11_room_notes_offline.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas")
data class Nota(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val titulo: String,

    val corpo: String,

    val dataCriacao: Long = System.currentTimeMillis()
)