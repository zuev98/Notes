package com.github.zuev98.notes

import android.os.Bundle
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NoteListFragment : Fragment(), NoteListFragmentView, NoteAdapter.OnNoteClickListener {
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
        adapter = NoteAdapter(mockData, this)
        noteRecyclerView.adapter = adapter
    }

    override fun pushFragment(noteId: UUID) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, NoteFragment.newInstance(noteId))
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onNoteClick(noteId: UUID) {
        noteListFragmentPresenter.onNoteClicked(noteId)
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
}