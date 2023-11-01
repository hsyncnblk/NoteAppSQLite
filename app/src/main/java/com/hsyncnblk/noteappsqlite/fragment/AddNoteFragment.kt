package com.hsyncnblk.noteappsqlite.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hsyncnblk.noteappsqlite.Note
import com.hsyncnblk.noteappsqlite.NotesDatabaseHelper
import com.hsyncnblk.noteappsqlite.R
import com.hsyncnblk.noteappsqlite.databinding.FragmentAddNoteBinding


class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var db: NotesDatabaseHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddNoteBinding.inflate(inflater,container,false)


        db=NotesDatabaseHelper(requireActivity())
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val note = Note(0,title,content)
            db.insertNote(note)
            Toast.makeText(requireActivity() , "Note Saved" , Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addNoteFragment_to_mainFragment)

        }


        return binding.root

    }




}