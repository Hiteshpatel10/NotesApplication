package com.example.notes.notesAdd

import android.os.Bundle
import android.text.Editable
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.database.Notes
import com.example.notes.database.NotesDatabase
import com.example.notes.databinding.FragmentNotesAddBinding


class NotesAddFragment : Fragment() {

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
    private val args: NotesAddFragmentArgs by navArgs()
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
        val application = requireNotNull(this.activity).application
        val dataBase = NotesDatabase.getDatabase(application).getNotesDao()
        val viewModelFactory = NotesAddViewModelFactory(dataBase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NotesAddViewModel::class.java)

        if (args.description.isNotEmpty()) {
            val title = args.titleText
            val description = args.description

            binding.noteTitleTextView.text = title.toEditable()
            binding.noteDescriptionTextView.text = description.toEditable()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val title = binding.noteTitleTextView.text.toString()
        val description = binding.noteDescriptionTextView.text.toString()

        return when (item.itemId) {
            R.id.submitButton -> {
                if (description.isNotEmpty()) {
                    viewModel.insert(Notes(noteTitle = title, noteText = description))
                    findNavController().navigate(R.id.notesFragment)
                }else{
                   Toast.makeText(requireContext(),"Note Missing",Toast.LENGTH_LONG).show()
                }

                true
            }
            R.id.deleteButton -> {
                if(args.id!=0){
                    viewModel.searchDelete(args.id)
                    findNavController().navigate(R.id.notesFragment)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
