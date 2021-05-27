package com.example.notes.notesAdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.database.Notes
import com.example.notes.database.NotesDao
import com.example.notes.database.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesAddViewModel(database: NotesDao): ViewModel() {

    val allNotes = database.getAllNotes()

    private val repository: NotesRepository = NotesRepository(database)

     fun insert(note: Notes){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(note)
        }
    }
}