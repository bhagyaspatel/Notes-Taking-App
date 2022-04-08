package com.example.mynotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "NotesTable")
class Note_entity (@ColumnInfo(name ="title") val noteTitle: String,
                    @ColumnInfo(name = "description") val noteDiscription : String,
                    @ColumnInfo(name = "timestamp")val timeStamp : String){
    @PrimaryKey (autoGenerate = true)
    var id = 0
}