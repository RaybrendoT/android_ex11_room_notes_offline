# Personalizar Lista de Notas e Adicionar Ações do DAO

O objetivo é aprimorar a tela `ListaNotasScreen.kt` para suportar as operações de exclusão e edição, além de adicionar Previews para facilitar o desenvolvimento.

## Alterações Propostas

### UI e Funcionalidade

#### [MODIFY] [ListaNotasScreen.kt](file:///C:/Users/raybr/AndroidStudioProjects/android_ex11_room_notes_offline/app/src/main/java/br/com/curso/android_ex11_room_notes_offline/screens/ListaNotasScreen.kt)
- **Componente `NotaItem`**: Extrair o layout de cada nota para um novo Composable, facilitando a manutenção e o Preview.
- **Ações de Nota**:
    - Adicionar um botão de exclusão (`IconButton` com ícone `Delete`) para chamar `viewModel.removerNota`.
    - Adicionar funcionalidade de edição. Podemos usar um diálogo simples ou expandir o card para editar o título e corpo.
- **Preview**: Adicionar Previews para o `NotaItem` e para a `ListaNotasScreen` (usando dados mockados).
- **Melhorias Visuais**: Usar `TopAppBar` para um visual mais padrão do Android e melhorar o layout dos cards.

## Plano de Verificação

### Testes Manuais
- Abrir o Preview no Android Studio para verificar o novo design.
- Executar o app e testar a exclusão de uma nota.
- Testar a edição de uma nota e verificar se os dados são persistidos corretamente.
