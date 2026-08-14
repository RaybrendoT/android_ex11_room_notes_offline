package br.com.curso.android_ex11_room_notes_offline.data.repository

import br.com.curso.android_ex11_room_notes_offline.data.local.Nota
import br.com.curso.android_ex11_room_notes_offline.data.local.NotaDao
import kotlinx.coroutines.flow.Flow

class NotaRepository(
    private val notaDao: NotaDao
) {

    fun listarNotas(): Flow<List<Nota>> {
        return notaDao.listar()
    }

    suspend fun inserir(nota: Nota) {
        notaDao.inserir(nota)
    }

    suspend fun atualizar(nota: Nota) {
        notaDao.atualizar(nota)
    }

    suspend fun remover(nota: Nota) {
        notaDao.remover(nota)
    }
}