package com.github.zuev98.notes

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NoteListFragment : Fragment(), NoteListFragmentView {
    interface Callbacks {
        fun onNoteSelected(noteID: UUID)
    }

    private var callbacks: Callbacks? = null
    private var adapter: NoteAdapter? = null
    private lateinit var noteListFragmentPresenter: NoteListFragmentPresenter
    private lateinit var noteRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        noteRecyclerView = view.findViewById(R.id.note_recycler_view) as RecyclerView
        noteRecyclerView.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(activity, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider, null)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        noteRecyclerView.addItemDecoration(dividerItemDecoration)

        noteListFragmentPresenter = NoteListFragmentPresenter(this)
        noteListFragmentPresenter.getMockData()
        return view
    }

    override fun updateUI(mockData: List<Note>) {
        adapter = NoteAdapter(mockData)
        noteRecyclerView.adapter = adapter
    }

    override fun callback(noteId: UUID) {
        callbacks?.onNoteSelected(noteId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_note_list, menu)
    }

    //Создание новой заметки
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_note -> {
                val note = Note()
                noteListFragmentPresenter.onNoteClicked(note.id)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun newInstance() = NoteListFragment()
    }

    private inner class NoteHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var note: Note

        private val noteHeading: TextView = itemView.findViewById(R.id.note_heading)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(note: Note) {
            this.note = note
            noteHeading.text = this.note.heading
        }

        override fun onClick(v: View?) {
            noteListFragmentPresenter.onNoteClicked(note.id)
        }
    }

    private inner class NoteAdapter(var notes: List<Note>) : RecyclerView.Adapter<NoteHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
            val view = layoutInflater.inflate(R.layout.list_item_note, parent, false)
            return NoteHolder(view)
        }

        override fun onBindViewHolder(holder: NoteHolder, position: Int) {
            val note = notes[position]
            holder.bind(note)
        }

        override fun getItemCount() = notes.size
    }
}