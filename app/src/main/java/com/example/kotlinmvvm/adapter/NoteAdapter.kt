package com.example.kotlinmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvvm.R
import com.example.kotlinmvvm.entities.Note


class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder?>() {

    private var notes: List<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context)
           .inflate(R.layout.note_item, parent, false)
       return NoteHolder(itemView)

    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote: Note = notes[position]
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
        holder.textViewPriority.text = currentNote.priority.toString()
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun getNoteAt(position: Int): Note? {
        return notes[position]
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class NoteHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val textViewTitle: TextView
        val textViewDescription: TextView
        val textViewPriority: TextView

        init {
            textViewTitle = itemView.findViewById(R.id.text_view_title)
            textViewDescription = itemView.findViewById(R.id.text_view_description)
            textViewPriority = itemView.findViewById(R.id.text_view_priority)
        }

    }


}
