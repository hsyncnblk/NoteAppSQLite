package com.hsyncnblk.noteappsqlite.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.hsyncnblk.noteappsqlite.Note
import com.hsyncnblk.noteappsqlite.NotesDatabaseHelper
import com.hsyncnblk.noteappsqlite.R
import com.hsyncnblk.noteappsqlite.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var db: NotesDatabaseHelper
    private  var noteId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = NotesDatabaseHelper(requireContext())

     //   noteId = arguments?.getInt("note_id", -1)!!
        val sharedPreferences = context?.getSharedPreferences("mySharedPrefernces", Context.MODE_PRIVATE)

        noteId = sharedPreferences!!.getInt("note_id",-1)

        if (noteId==-1){
            activity?.finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateSaveButton.setOnClickListener {

            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updateNote = Note(noteId,newTitle,newContent)

            db.updateNote(updateNote)


            Toast.makeText(requireActivity(),"changes saved",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_mainFragment)

        }


    }


}