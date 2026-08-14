package br.com.curso.android_ex11_room_notes_offline.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.curso.android_ex11_room_notes_offline.data.local.Nota
import br.com.curso.android_ex11_room_notes_offline.viewmodel.NotaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaNotasScreen(
    viewModel: NotaViewModel,
    onNovaNota: () -> Unit
) {
    val notas by viewModel.notas.collectAsState()
    var notaParaEditar by remember { mutableStateOf<Nota?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Minhas Notas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNovaNota
            ) {
                Text("+")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = notas,
                key = { it.id }
            ) { nota ->
                NotaItem(
                    nota = nota,
                    onDelete = { viewModel.removerNota(nota) },
                    onEdit = { notaParaEditar = nota }
                )
            }
        }
    }

    // Diálogo de Edição
    notaParaEditar?.let { nota ->
        EditarNotaDialog(
            nota = nota,
            onDismiss = { notaParaEditar = null },
            onConfirm = { notaAtualizada ->
                viewModel.atualizarNota(notaAtualizada)
                notaParaEditar = null
            }
        )
    }
}

@Composable
fun NotaItem(
    nota: Nota,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = nota.titulo,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = nota.corpo,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = onEdit) {
                Icon(Icons.Default.Edit, contentDescription = "Editar")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Excluir")
            }
        }
    }
}

@Composable
fun EditarNotaDialog(
    nota: Nota,
    onDismiss: () -> Unit,
    onConfirm: (Nota) -> Unit
) {
    var titulo by remember { mutableStateOf(nota.titulo) }
    var corpo by remember { mutableStateOf(nota.corpo) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Nota") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") }
                )
                OutlinedTextField(
                    value = corpo,
                    onValueChange = { corpo = it },
                    label = { Text("Corpo") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(nota.copy(titulo = titulo, corpo = corpo)) },
                enabled = titulo.isNotBlank() && corpo.isNotBlank()
            ) {
                Text("Salvar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NotaItemPreview() {
    NotaItem(
        nota = Nota(id = 1, titulo = "Título da Nota", corpo = "Conteúdo da nota para o preview."),
        onDelete = {},
        onEdit = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ListaNotasScreenPreview() {
    // Nota: Preview da tela completa exigiria um ViewModel mockado ou 
    // refatorar a tela para aceitar uma lista de notas diretamente.
    // Para simplificar o preview da UI:
    val notasExemplo = listOf(
        Nota(1, "Nota 1", "Corpo 1"),
        Nota(2, "Nota 2", "Corpo 2")
    )
    
    Scaffold(
        topBar = { TopAppBar(title = { Text("Preview Notas") }) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(notasExemplo) { nota ->
                NotaItem(nota = nota, onDelete = {}, onEdit = {})
            }
        }
    }
}
