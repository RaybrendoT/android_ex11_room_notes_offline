package br.com.curso.android_ex11_room_notes_offline


import android.app.Application
import br.com.curso.android_ex11_room_notes_offline.data.local.AppDatabase
import br.com.curso.android_ex11_room_notes_offline.data.repository.NotaRepository

class NotesApplication : Application() {

    val database by lazy {
        AppDatabase.getDatabase(this)
    }

    val repository by lazy {
        NotaRepository(database.notaDao())
    }
}