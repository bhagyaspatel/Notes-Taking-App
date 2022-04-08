package com.example.mynotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class notesViewModal(application: Application) : AndroidViewModel(application) {
    val allNotes : LiveData <List<Note_entity>>
    val repository : NotesRepository

    init{
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NotesRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote (note : Note_entity) = viewModelScope.launch (Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote (note : Note_entity) = viewModelScope.launch (Dispatchers.IO) {
        repository.update(note)
    }

    fun addNote (note : Note_entity) = viewModelScope.launch (Dispatchers.IO) {
        repository.insert(note)
    }


}