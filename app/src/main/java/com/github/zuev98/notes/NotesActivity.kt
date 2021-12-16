package com.github.zuev98.notes
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar

class NotesActivity : AppCompatActivity(), NoteView {
    private lateinit var notesPresenter: NotesPresenter
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        notesPresenter = NotesPresenter(this)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = NoteListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_about -> {
                notesPresenter.onAboutItemSelected()
                true
            }
            else -> false
        }
    }

    override fun openAboutScreen() {
        startActivity(Intent(this, AboutActivity::class.java))
    }

    override fun onDestroy() {
        notesPresenter.onDestroy()
        super.onDestroy()
    }
}