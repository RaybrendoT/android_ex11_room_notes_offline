from pathlib import Path

readme = """# 📝 Android Ex11 — Room Notes Offline

Aplicativo Android desenvolvido em **Kotlin** com **Jetpack Compose**, criado para demonstrar o armazenamento local de notas utilizando o **Room Database**, com arquitetura baseada em **Repository + ViewModel** e navegação entre telas.

## 📱 Sobre o projeto

O projeto implementa um aplicativo simples de anotações que permite:

- Criar novas notas;
- Listar as notas salvas;
- Atualizar notas;
- Remover notas;
- Persistir os dados localmente no dispositivo;
- Utilizar a aplicação sem depender de uma API ou banco de dados remoto.

Os dados das notas são armazenados em uma tabela local chamada `notas`, utilizando o Room. A entidade `Nota` possui `id`, `titulo`, `corpo` e `dataCriacao`.

## ✨ Funcionalidades

### ➕ Criar nota
A tela de criação permite informar o título e o conteúdo da nota. Ao salvar, a nota é enviada ao `ViewModel` e persistida no banco local.

### 📋 Listar notas
As notas são observadas por meio de `Flow` e disponibilizadas para a interface através de um `StateFlow`, mantendo a tela atualizada quando os dados do banco são alterados.

### ✏️ Atualizar nota
Uma nota existente pode ser atualizada utilizando o método `atualizar()` do DAO.

### 🗑️ Remover nota
Notas podem ser excluídas diretamente do banco local.

### 📦 Funcionamento offline
Como os dados ficam armazenados localmente com Room, o aplicativo não precisa de conexão com a internet para realizar as operações de notas.

## 🏗️ Arquitetura

O projeto utiliza uma separação simples de responsabilidades:

```text
UI — Jetpack Compose
        │
        ▼
    ViewModel
        │
        ▼
   Repository
        │
        ▼
      DAO
        │
        ▼
   Room Database
        │
        ▼
  SQLite / dispositivo
