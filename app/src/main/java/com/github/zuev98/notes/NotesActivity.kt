package com.github.zuev98.notes
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    private lateinit var shareButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        initViews()
        notesPresenter = NotesPresenter(this)
    }

    private fun initViews() {
        headingEditText = findViewById(R.id.heading_input)
        noteEditText = findViewById(R.id.note_input)
        notesTextView = findViewById(R.id.notes_text_view)

        saveButton = findViewById<Button>(R.id.save_button).also {
            it.setOnClickListener {
                notesPresenter.onSaveBtnClicked(
                    headingEditText.text.toString(),
                    noteEditText.text.toString()
                )
            }
        }

        shareButton = findViewById<Button>(R.id.share_button).also {
            it.setOnClickListener {
                notesPresenter.onShareBtnClicked(notesTextView.text.toString())
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            notesPresenter.onAboutItemSelected()
        }
        return true
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

    override fun openAboutScreen() {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    override fun shareNotes(text: String) {
        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        })
    }

    override fun shareDataFailed() {
        Toast.makeText(this, getString(R.string.share_failed), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        notesPresenter.onDestroy()
        super.onDestroy()
    }
}