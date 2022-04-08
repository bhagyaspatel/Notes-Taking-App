package com.example.mynotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesRVAdapter (val context: Context,
                      val dltListener : NotesDltInterface,
                      val editListener : NotesEditInterface) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    private val notes = ArrayList <Note_entity>()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var noteTitle = itemView.findViewById<TextView>(R.id.title)
        val timeStamp = itemView.findViewById<TextView>(R.id.timestamp)
        val dltBtn = itemView.findViewById<ImageView>(R.id.deletBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle.setText(notes.get(position).noteTitle)
        holder.timeStamp.setText("Last updated: " + notes.get(position).timeStamp)

        holder.dltBtn.setOnClickListener{
            dltListener.onDltClick(notes.get(position))
        }

        holder.itemView.setOnClickListener{
            editListener.onNoteClick(notes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateList (list : List<Note_entity>){
        notes.clear()
        notes.addAll(list)
        notifyDataSetChanged()
    }
}

interface NotesEditInterface {
    fun onNoteClick (note : Note_entity)
}

interface NotesDltInterface {
    fun onDltClick (note : Note_entity)
}