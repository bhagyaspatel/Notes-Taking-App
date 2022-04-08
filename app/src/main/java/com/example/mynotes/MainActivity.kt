package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NotesDltInterface, NotesEditInterface {

    lateinit var noteRv : RecyclerView
    lateinit var addFloat : FloatingActionButton
    lateinit var viewModal : notesViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRv = findViewById(R.id.notesRV)
        addFloat = findViewById(R.id.floatingAdd)

        noteRv.layoutManager = LinearLayoutManager(this)
        val notesRVAdapter = NotesRVAdapter (this,this,this)
        noteRv.adapter = notesRVAdapter

        viewModal = ViewModelProvider (this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(notesViewModal::class.java)
        viewModal.allNotes.observe(this, { list ->
            list?.let{
                notesRVAdapter.updateList(it)
            }
        })
        addFloat.setOnClickListener{
            val intent = Intent (this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDltClick(note: Note_entity) {
        viewModal.deleteNote(note)
        Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: Note_entity) {
        val intent = Intent (this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDiscription", note.noteDiscription)
        intent.putExtra("noteId", note.id)

        startActivity(intent)
        this.finish()
    }
}