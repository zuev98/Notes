package com.github.zuev98.notes

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class NoteFragment : Fragment(), NoteFragmentView {
    private lateinit var noteFragmentPresenter: NoteFragmentPresenter
    private lateinit var note: Note
    private lateinit var headingField: EditText
    private lateinit var noteField: EditText
    private lateinit var dateField: TextView
    private lateinit var shareButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //note = Note()
        val noteId: UUID = arguments?.getSerializable(ARG_NOTE_ID) as UUID
        note = NoteRepository.getNote(noteId)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note, container, false)
        initViews(view)
        noteFragmentPresenter = NoteFragmentPresenter(this)

        return view
    }

    private fun initViews(view: View) {
        headingField = view.findViewById(R.id.heading_edit)
        noteField = view.findViewById(R.id.note_edit)
        dateField = view.findViewById(R.id.note_date)

        shareButton = view.findViewById<Button>(R.id.share_button).also {
            it.setOnClickListener {
                noteFragmentPresenter.onShareBtnClicked(
                    headingField.text.toString(),
                    noteField.text.toString())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headingField.setText(note.heading)
        noteField.setText(note.text)
        dateField.text = SimpleDateFormat("EEEE, MMM dd, yyyy.", Locale.US).format(note.date)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_note, menu)
    }

    //Сохранение заметки
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                noteFragmentPresenter.onSaveBtnClicked(
                    headingField.text.toString(),
                    noteField.text.toString())
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun addNotesTextView(text: String) {
        showToast(text)
    }

    override fun getDataFailed(error: String) {
        showToast(error)
    }

    override fun shareNote(heading: String, text: String) {
        startActivity(Intent(Intent.ACTION_SEND).apply {
            val note = "$heading\n$text"
            type = TEXT_TYPE
            putExtra(Intent.EXTRA_TEXT, note)
        })
    }

    override fun shareDataFailed() {
        showToast(getString(R.string.share_failed))
    }

    private fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val TEXT_TYPE = "text/plain"
        private const val ARG_NOTE_ID = "note_id"

        fun newInstance(noteId: UUID): NoteFragment {
            val args = Bundle().apply {
                putSerializable(ARG_NOTE_ID, noteId)
            }
            return NoteFragment().apply {
                arguments = args
            }
        }
    }
}