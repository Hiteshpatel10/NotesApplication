package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.database.Notes

class NotesListAdapter(private val listener: INotesListAdapter) : RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    private var allNotes = listOf<Notes>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.notes_list_recyclerview, parent, false)
        )
        viewHolder.textView.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = allNotes[position]
        holder.bind(item)
    }


    override fun getItemCount(): Int {
        return allNotes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.notesText)

        fun bind(text: Notes) {
            textView.text = text.noteText
        }
    }

    interface INotesListAdapter {
        fun onItemClicked(note: Notes)
    }
}