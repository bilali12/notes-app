package com.allescoders.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.allescoders.notesapp.dao.NoteDAO
import com.allescoders.notesapp.databinding.ActivityAddNoteBinding
import com.allescoders.notesapp.databinding.ActivityMainBinding
import com.allescoders.notesapp.model.Note

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NoteDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDAO(this)

        binding.saveBtn.setOnClickListener {
            val title = binding.txtTitle.text.toString()
            val content = binding.txtNote.text.toString()
            val note = Note(0, title, content)
            db.insertNote(note)
            finish()
            Toast.makeText(this, "Note ajoutée avec succès", Toast.LENGTH_SHORT).show()
        }
    }
}