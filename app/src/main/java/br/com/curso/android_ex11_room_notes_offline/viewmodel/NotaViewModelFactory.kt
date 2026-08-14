package br.com.curso.android_ex11_room_notes_offline.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.curso.android_ex11_room_notes_offline.data.repository.NotaRepository

class NotaViewModelFactory(
    private val repository: NotaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(NotaViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return NotaViewModel(repository) as T
        }

        throw IllegalArgumentException(
            "ViewModel desconhecido"
        )
    }
}