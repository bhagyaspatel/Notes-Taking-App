package com.example.mynotes

import androidx.lifecycle.LiveData

class NotesRepository (private val notesDao: NotesDao) {

    val allNotes : LiveData <List <Note_entity>> = notesDao.getAllNotes()

    suspend fun insert (note : Note_entity){
        notesDao.insert(note)
    }

    suspend fun delete (note : Note_entity){
        notesDao.delete(note)
    }

    suspend fun update (note: Note_entity){
        notesDao.update(note)
    }
}