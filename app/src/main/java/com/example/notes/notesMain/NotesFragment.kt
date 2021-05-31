package com.example.notes.notesMain

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notes.NotesListAdapter
import com.example.notes.R
import com.example.notes.database.Notes
import com.example.notes.database.NotesDatabase
import com.example.notes.databinding.FragmentNotesBinding


class NotesFragment : Fragment(), NotesListAdapter.INotesListAdapter,
    SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var viewModel: NotesViewModel
    private lateinit var adapter: NotesListAdapter
    private val timeDelay = 2000
    private var backPressed: Long = 0

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
        adapter = NotesListAdapter(this)
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
                    NotesFragmentDirections.actionNotesFragmentToNotesAddFragment("", "", 0)
                findNavController().navigate(arguments)
            } catch (e: Exception) {
                Log.i("noteAdd", "$e")
            }
        }

        //DoubleBack Press To Exit
        requireActivity().onBackPressedDispatcher.addCallback(this) {

            if (backPressed + timeDelay > System.currentTimeMillis()) {
                activity?.moveTaskToBack(true)
                activity?.finish()
            } else {
                Toast.makeText(
                    requireContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            backPressed = System.currentTimeMillis();
        }



        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            if (query.isNotEmpty()) {
                searchDatabase(query)
            }
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            if (query.isNotEmpty()) {
                searchDatabase(query)
            } else {
                viewModel.allNotes.observe(this, {
                    adapter.allNotes = it
                })
            }
        }
        return true
    }

    private fun searchDatabase(query: String?) {
        val searchQuery = "%$query%"

        viewModel.searchNote(searchQuery).observe(this, {
            it.let {
                adapter.setData(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_menu, menu)
        val search = menu.findItem(R.id.menuSearchNav)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
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