package com.example.notes.notesMain

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notes.NotesListAdapter
import com.example.notes.R
import com.example.notes.database.Notes
import com.example.notes.database.NotesDatabase
import com.example.notes.databinding.FragmentNotesBinding
import java.lang.Exception

class NotesFragment : Fragment(), NotesListAdapter.INotesListAdapter {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_notes,
            container,
            false
        )
        //Initialization
        val application = requireNotNull(this.activity).application
        val database = NotesDatabase.getDatabase(application).getNotesDao()
        val viewModelFactory = NotesViewModelFactory(database)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(NotesViewModel::class.java)


        //Adapter and RecyclerView
        val adapter = NotesListAdapter(this)
        binding.notesRecyclerView.adapter = adapter

        //Observers
        viewModel.allNotes.observe(viewLifecycleOwner, {
            it?.let {
                adapter.allNotes = it
            }
        })

        //OnClickListeners
        binding.floatingActionButton.setOnClickListener {
            try {
                val arguments =
                    NotesFragmentDirections.actionNotesFragmentToNotesAddFragment("", "",0)
                findNavController().navigate(arguments)
            } catch (e: Exception) {
                Log.i("noteAdd", "$e")
            }
        }

        return binding.root
    }

    override fun onItemClicked(note: Notes) {

        val arguments =
            NotesFragmentDirections.actionNotesFragmentToNotesAddFragment(
                note.noteTitle,
                note.noteText,
                note.noteId
            )
        findNavController().navigate(arguments)
    }
}