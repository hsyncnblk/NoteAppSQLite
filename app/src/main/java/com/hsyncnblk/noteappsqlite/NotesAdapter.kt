package com.hsyncnblk.noteappsqlite


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hsyncnblk.noteappsqlite.databinding.NoteItemBinding


class NotesAdapter(private var notes : List<Note> , private val context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val db:NotesDatabaseHelper = NotesDatabaseHelper(context)

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

        holder.noteItemBinding.updateButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_updateFragment)


//            val bundle = Bundle()
//            bundle.putInt("note_id",note.id)

            val sharedPrefernces = context.getSharedPreferences("mySharedPrefernces",Context.MODE_PRIVATE)
            val editor = sharedPrefernces.edit()

            editor.putInt("note_id",note.id)
            editor.apply()
        }

        holder.noteItemBinding.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(context,"Note deleted",Toast.LENGTH_SHORT).show()
        }



    }

    fun refreshData(newNotes : List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}