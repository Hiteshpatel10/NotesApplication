package com.example.notes.notesMain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.database.NotesDao
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class NotesViewModelFactory(private val dataSource: NotesDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NotesViewModel::class.java)){
            return NotesViewModelFactory(dataSource) as T
        }
        throw IllegalArgumentException("NotesViewModel: Unknown Error")
    }
}