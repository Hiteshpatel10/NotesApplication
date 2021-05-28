package com.example.notes.database

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    fun insert(note: Notes) {
        notesDao.insert(note)
    }

    fun delete(note: Notes) {
        notesDao.delete(note)
    }

    fun searchDelete(id: Int){
        notesDao.searchDelete(id)
    }

    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()
}