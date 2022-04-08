package com.example.mynotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE )
    fun insert (note : Note_entity)

    @Update
    fun update (note : Note_entity)

    @Delete
    fun delete (note: Note_entity)

    @Query ("SELECT * from NotesTable order by id ASC")
    fun getAllNotes() : LiveData <List<Note_entity>>

}