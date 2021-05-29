package com.example.notes.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

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

    fun searchNote(search: String): Flow<List<Notes>>{
        return notesDao.searchNote(search)
    }

    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()
}