package com.example.notes.notesMain

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.notes.database.NotesDao
import com.example.notes.database.NotesRepository

class NotesViewModel(database: NotesDao): ViewModel() {

    val repository: NotesRepository = NotesRepository(database)

    val allNotes = repository.allNotes
}