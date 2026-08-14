package br.com.curso.android_ex11_room_notes_offline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.curso.android_ex11_room_notes_offline.screens.ListaNotasScreen
import br.com.curso.android_ex11_room_notes_offline.screens.NovaNotaScreen
import br.com.curso.android_ex11_room_notes_offline.ui.theme.Android_ex11_room_notes_offlineTheme
import br.com.curso.android_ex11_room_notes_offline.viewmodel.NotaViewModel
import br.com.curso.android_ex11_room_notes_offline.viewmodel.NotaViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: NotaViewModel by viewModels {

        val application = application as NotesApplication

        NotaViewModelFactory(
            application.repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Android_ex11_room_notes_offlineTheme {

                NotesApp(
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun NotesApp(
    viewModel: NotaViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "lista_notas"
    ) {

        composable("lista_notas") {

            ListaNotasScreen(
                viewModel = viewModel,
                onNovaNota = {
                    navController.navigate("nova_nota")
                }
            )
        }

        composable("nova_nota") {

            NovaNotaScreen(
                viewModel = viewModel,
                onVoltar = {
                    navController.popBackStack()
                }
            )
        }
    }
}