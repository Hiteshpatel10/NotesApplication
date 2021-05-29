package com.example.notes.notesMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.notes.database.Notes
import com.example.notes.database.NotesDao
import com.example.notes.database.NotesRepository

class NotesViewModel(database: NotesDao): ViewModel() {

    val repository: NotesRepository = NotesRepository(database)

    val allNotes = repository.allNotes

    fun searchNote(search: String): LiveData<List<Notes>>{
        return repository.searchNote(search).asLiveData()
    }


}