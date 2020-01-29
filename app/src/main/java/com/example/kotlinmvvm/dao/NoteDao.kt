package com.example.kotlinmvvm.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlinmvvm.entities.Note

@Dao
interface NoteDao {

    @Insert
    fun insert (note : Note)

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note: Note)

    @Query("SELECT * FROM note ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Note>>?

    @Query("DELETE FROM note")
    fun deleteAllNotes()
}