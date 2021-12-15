package com.github.zuev98.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NoteAdapter(var notes: List<Note>, val onNoteClickListener: OnNoteClickListener)
    : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    interface OnNoteClickListener {
        fun onNoteClick(noteId: UUID)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_note, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount() = notes.size

    inner class NoteHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var note: Note

        private val noteHeading: TextView = itemView.findViewById(R.id.note_heading)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(note: Note) {
            this.note = note
            noteHeading.text = this.note.heading
        }

        override fun onClick(v: View?) {
            onNoteClickListener.onNoteClick(note.id)
        }
    }
}