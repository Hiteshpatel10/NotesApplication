package com.example.notes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert
    fun insert(notes: Notes)

    @Delete
    fun delete(notes: Notes)

    @Query("SELECT * FROM notes_table ORDER BY noteId ASC")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("DELETE FROM notes_table WHERE noteId = :id")
    fun searchDelete(id: Int)

    @Query("SELECT * FROM notes_table WHERE note_text LIKE :search OR note_title LIKE :search")
    fun searchNote(search: String): Flow<List<Notes>>
}