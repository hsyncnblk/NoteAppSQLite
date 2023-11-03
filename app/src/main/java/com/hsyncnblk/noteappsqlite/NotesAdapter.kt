package com.hsyncnblk.noteappsqlite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hsyncnblk.noteappsqlite.databinding.NoteItemBinding

class NotesAdapter(private var notes : List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(val noteItemBinding: NoteItemBinding) : RecyclerView.ViewHolder(noteItemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val noteItemBinding = NoteItemBinding.inflate(layoutInflater,parent,false)
        return NoteViewHolder(noteItemBinding)
    }

    override fun getItemCount(): Int  = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.noteItemBinding.titleTv.text = note.title
        holder.noteItemBinding.contentTv.text = note.content
    }

    fun refreshData(newNotes : List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}