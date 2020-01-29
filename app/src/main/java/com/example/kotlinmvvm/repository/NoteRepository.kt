package com.example.kotlinmvvm.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.kotlinmvvm.dao.NoteDao
import com.example.kotlinmvvm.database.NoteDatabase
import com.example.kotlinmvvm.entities.Note


class NoteRepository(application: Application) {

    private val noteDao: NoteDao
    private val allNotes: LiveData<List<Note>>?

    init {
        val database = NoteDatabase.getInstance(application)
        noteDao = database.noteDao
        allNotes = noteDao.getAllNotes()
    }

     fun insert(note: Note){
        InsertNote(noteDao).execute(note)
    }

    fun update(note: Note){
        UpdateNote(noteDao).execute(note)
    }

    fun delete(note: Note){
        DeleteNote(noteDao).execute(note)
    }

    fun deleteAll(){
        DeleteAllNotes(noteDao).execute()
    }

    fun getAllNotes():LiveData<List<Note>>?{
        return allNotes
    }

    private class InsertNote(val noteDao: NoteDao): AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.insert(notes[0])
            return null
        }

    }

    private class DeleteNote(val noteDao: NoteDao) : AsyncTask<Note,Void,Void>(){

        override fun doInBackground(vararg notes: Note): Void? {
            noteDao.delete(notes[0])
            return null
        }

    }

    private class UpdateNote(val noteDao: NoteDao) : AsyncTask<Note,Void,Void>(){

        override fun doInBackground(vararg notes: Note): Void? {

            noteDao.update(notes[0])
            return  null
        }

    }

    private class DeleteAllNotes(val noteDao: NoteDao) : AsyncTask<Note,Void,Void>(){

        override fun doInBackground(vararg params: Note?): Void? {

            noteDao.deleteAllNotes()
            return null
        }

    }


}