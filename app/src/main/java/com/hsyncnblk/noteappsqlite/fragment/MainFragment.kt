package com.hsyncnblk.noteappsqlite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hsyncnblk.noteappsqlite.NotesAdapter
import com.hsyncnblk.noteappsqlite.NotesDatabaseHelper
import com.hsyncnblk.noteappsqlite.R
import com.hsyncnblk.noteappsqlite.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var db: NotesDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = NotesDatabaseHelper(requireActivity())
        notesAdapter = NotesAdapter(db.getAllNotes(),requireActivity())

        binding.notesRV.layoutManager=LinearLayoutManager(requireActivity())
        binding.notesRV.adapter=notesAdapter

        binding.addButton.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.main_to_add)

        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }


}