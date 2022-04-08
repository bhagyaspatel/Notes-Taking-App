package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var noteTitle : EditText
    lateinit var noteDescription : EditText
    lateinit var addBtn : androidx.appcompat.widget.AppCompatButton
    lateinit var viewModal :notesViewModal

    var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteTitle = findViewById(R.id.NotesTitle)
        noteDescription = findViewById(R.id.NoteDescription)
        addBtn = findViewById(R.id.addBtn)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(notesViewModal::class.java)

        val noteType = intent.getStringExtra("noteType")

        if (noteType == "edit") {
            val noteTitleTxt = intent.getStringExtra("noteTitle")
            val noteDiscriptionTxt = intent.getStringExtra("noteDiscription")
            noteId = intent.getIntExtra("noteId", -1)

            addBtn.setText("Update Note")
            noteTitle.setText(noteTitleTxt)
            noteDescription.setText(noteDiscriptionTxt)
        } else {
            addBtn.setText("Save Note")
        }

        addBtn.setOnClickListener {
            val noteTitleTxt = noteTitle.text.toString()
            val noteDescriptionTxt = noteDescription.text.toString()

            if (noteType.equals("edit")) {
                if (noteTitleTxt.isNotEmpty() || noteDescriptionTxt.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())

                    val updateNote = Note_entity(noteTitleTxt, noteDescriptionTxt, currentDate)
                    updateNote.id = noteId

                    viewModal.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (noteTitleTxt.isNotEmpty() && noteDescriptionTxt.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val newNote = Note_entity(noteTitleTxt, noteDescriptionTxt, currentDate)

                    viewModal.addNote(newNote)
                    Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                }
            }

            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}