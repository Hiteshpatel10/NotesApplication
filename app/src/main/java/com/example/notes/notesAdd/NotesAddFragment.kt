package com.example.notes.notesAdd

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.database.Notes
import com.example.notes.database.NotesDatabase
import com.example.notes.databinding.FragmentNotesAddBinding

class NotesAddFragment : Fragment() {

    private lateinit var binding: FragmentNotesAddBinding
    private lateinit var viewModel: NotesAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_notes_add,
            container,
            false
        )

        //Initialize
        val dataBase = NotesDatabase.getDatabase(requireContext()).getNotesDao()
        val viewModelFactory = NotesAddViewModelFactory(dataBase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NotesAddViewModel::class.java)

        binding.submit.setOnClickListener {
            val title = binding.noteTitleText.text.toString()
            val description = binding.noteDescriptionText.text.toString()

            if (description.isNotEmpty()) {
                viewModel.insert(Notes(noteTitle = title, noteText = description))
                Log.i("notesAdd", "suces")
            }
        }

        return binding.root
    }
}