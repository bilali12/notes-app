package com.allescoders.notesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.allescoders.notesapp.R
import com.allescoders.notesapp.model.Note

class NoteAdapter(context: Context,
    private var notes: List<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

        class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val titleTextView: TextView = itemView.findViewById(R.id.txtTitle)
            val contentTextView: TextView = itemView.findViewById(R.id.txtContent)

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
    }

    fun refreshNote(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}