package br.com.curso.android_ex11_room_notes_offline.data.local


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(nota: Nota)

    @Query("SELECT * FROM notas ORDER BY dataCriacao DESC")
    fun listar(): Flow<List<Nota>>

    @Update
    suspend fun atualizar(nota: Nota)

    @Delete
    suspend fun remover(nota: Nota)
}