package com.example.notes.notesAdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.notesMain.NotesViewModelFactory
import com.example.notes.database.NotesDao

@Suppress("UNCHECKED_CAST")
class NotesAddViewModelFactory(private val dataSource: NotesDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NotesAddViewModel::class.java)){
            return NotesViewModelFactory(dataSource) as T
        }
        throw IllegalArgumentException("NotesAddViewModel: Unknown Error")
    }
}