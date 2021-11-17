package com.github.zuev98.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class NotesActivity : AppCompatActivity(), NotesContract.View {
    private lateinit var notesPresenter: NotesPresenter
    private lateinit var headingEditText: EditText
    private lateinit var noteEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var notesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        notesPresenter = NotesPresenter(this, NotesModel())
        headingEditText = findViewById(R.id.heading_input)
        noteEditText = findViewById(R.id.note_input)
        saveButton = findViewById(R.id.save_button)
        notesTextView = findViewById(R.id.notes_text_view)
        notesTextView.movementMethod = ScrollingMovementMethod()

        saveButton.setOnClickListener { notesPresenter.onButtonClick(
            headingEditText.text.toString(),
            noteEditText.text.toString())
        }
    }

    override fun addNotesTextView(text: String) {
        notesTextView.append(text)
        headingEditText.setText("")
        noteEditText.setText("")
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
    }

    override fun getDataFailed(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        notesPresenter.onDestroy()
        super.onDestroy()
    }
}