package com.github.zuev98.notes
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NotesActivity : AppCompatActivity(), View {
    private lateinit var notesPresenter: NotesPresenter
    private lateinit var headingEditText: EditText
    private lateinit var noteEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var notesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        initViews()
        notesPresenter = NotesPresenter(this)
    }

    private fun initViews() {
        headingEditText = findViewById(R.id.heading_input)
        noteEditText = findViewById(R.id.note_input)
        saveButton = findViewById(R.id.save_button)
        notesTextView = findViewById(R.id.notes_text_view)

        saveButton.setOnClickListener {
            notesPresenter.onButtonClick(
                headingEditText.text.toString(),
                noteEditText.text.toString())
        }
    }

    override fun addNotesTextView(text: String) {
        notesTextView.append(text)
        headingEditText.setText("")
        noteEditText.setText("")
        Toast.makeText(this, R.string.saved, Toast.LENGTH_LONG).show()
    }

    override fun getDataFailed(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        notesPresenter.onDestroy()
        super.onDestroy()
    }
}