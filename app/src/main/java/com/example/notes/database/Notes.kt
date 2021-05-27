package com.example.notes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(
    @ColumnInfo(name = "note_title")
    val noteTitle: String,

    @ColumnInfo(name = "note_text")
    val noteText: String
){
    @PrimaryKey(autoGenerate = true)
    var noteId = 0
}