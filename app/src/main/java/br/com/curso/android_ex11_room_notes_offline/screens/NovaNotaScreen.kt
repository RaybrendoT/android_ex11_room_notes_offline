package br.com.curso.android_ex11_room_notes_offline.screens



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.curso.android_ex11_room_notes_offline.viewmodel.NotaViewModel

@Composable
fun NovaNotaScreen(
    viewModel: NotaViewModel,
    onVoltar: () -> Unit
) {

    var titulo by rememberSaveable {
        mutableStateOf("")
    }

    var corpo by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Nova Nota"
        )

        OutlinedTextField(
            value = titulo,
            onValueChange = {
                titulo = it
            },
            label = {
                Text("Título")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = corpo,
            onValueChange = {
                corpo = it
            },
            label = {
                Text("Corpo")
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 5
        )

        Button(
            onClick = {

                viewModel.inserirNota(
                    titulo = titulo,
                    corpo = corpo
                )

                onVoltar()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = titulo.isNotBlank() && corpo.isNotBlank()
        ) {
            Text("Salvar Nota")
        }

        Button(
            onClick = onVoltar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }
}