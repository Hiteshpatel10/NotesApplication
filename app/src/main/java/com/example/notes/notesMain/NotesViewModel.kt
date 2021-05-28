package com.example.notes.notesMain

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.database.Notes
import com.example.notes.database.NotesDao
import com.example.notes.database.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(database: NotesDao): ViewModel() {

    val repository: NotesRepository = NotesRepository(database)

    val allNotes = repository.allNotes

}