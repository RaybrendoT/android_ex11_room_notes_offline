package br.com.curso.android_ex11_room_notes_offline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.curso.android_ex11_room_notes_offline.data.local.Nota
import br.com.curso.android_ex11_room_notes_offline.data.repository.NotaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotaViewModel(
    private val repository: NotaRepository
) : ViewModel() {

    val notas: StateFlow<List<Nota>> =
        repository.listarNotas()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun inserirNota(
        titulo: String,
        corpo: String
    ) {
        viewModelScope.launch {

            val nota = Nota(
                titulo = titulo,
                corpo = corpo
            )

            repository.inserir(nota)
        }
    }

    fun removerNota(nota: Nota) {
        viewModelScope.launch {
            repository.remover(nota)
        }
    }

    fun atualizarNota(nota: Nota) {
        viewModelScope.launch {
            repository.atualizar(nota)
        }
    }
}