package com.example.kotlinmvvm.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvm.R
import com.example.kotlinmvvm.adapter.NoteAdapter
import com.example.kotlinmvvm.entities.Note
import com.example.kotlinmvvm.utils.ADD_NOTE_REQUEST
import com.example.kotlinmvvm.utils.showToast
import com.example.kotlinmvvm.viewModel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private var noteViewModel: NoteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAddNote: FloatingActionButton = findViewById(R.id.button_add_note)
        buttonAddNote.setOnClickListener{
                val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
                startActivityForResult(intent, ADD_NOTE_REQUEST)
            }

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter = NoteAdapter()
        recyclerView.adapter = adapter

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel?.getAllNotes()?.observe(this, Observer {
        showToast("onChanged ${it.size}")
            adapter.setNotes(it)
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel!!.delete(adapter.getNoteAt(viewHolder.adapterPosition)!!)
              showToast( "Note deleted")
            }
        }).attachToRecyclerView(recyclerView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {

            val title: String = data?.getStringExtra(AddNoteActivity.EXTRA_TITLE).toString()
            val description: String =
                data?.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION).toString()
            val priority: Int = data?.getIntExtra(AddNoteActivity.EXTRA_PRIORITY,  1)!!
            val note = Note(0,title, description, priority)
            noteViewModel!!.insert(note)
            showToast("Note saved")
        } else {
            showToast("Note not saved")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel?.deleteAll()
                showToast( "All notes deleted")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}