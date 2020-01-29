package com.example.kotlinmvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kotlinmvvm.entities.Note
import com.example.kotlinmvvm.repository.NoteRepository


class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: NoteRepository? = null
    private var allNotes: LiveData<List<Note>>? = null

    init {
        repository = NoteRepository(application)
        allNotes = repository!!.getAllNotes()
    }

    fun insert(note: Note){
        repository?.insert(note)
    }

    fun update(note: Note){
        repository?.update(note)
    }

    fun delete(note: Note){
        repository?.delete(note)
    }

    fun deleteAll(){
        repository?.deleteAll()
    }

    fun getAllNotes():LiveData<List<Note>>?{
        return allNotes
    }

}