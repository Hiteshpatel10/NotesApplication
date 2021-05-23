package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {

    @Insert
    fun insert(notes: Notes)

    @Delete
    fun delete(notes: Notes)

    @Query("SELECT * FROM notes_table ORDER BY noteId ASC")
    fun getAllNotes() : LiveData<List<Notes>>
}