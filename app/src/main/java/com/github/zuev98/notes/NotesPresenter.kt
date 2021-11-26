package com.github.zuev98.notes

/**
 * Presenter for [NotesActivity]
 *
 * @param notesView view which can be accessed through the interface
 */
class NotesPresenter(private var notesView: View?) {

    /**
     * Handling of clicking on the "save" button
     *
     * @param heading note title
     * @param note note text
     */
    fun onSaveBtnClicked(heading: String, note: String) {
        when {
            heading.isBlank() && note.isBlank() -> onEmptyError("Fill in the fields!")
            heading.isBlank() -> onEmptyError("Fill in the heading!")
            note.isBlank() -> onEmptyError("Fill in the note!")
            else -> onSuccess("$heading:\n$note\n\n")
        }
    }

    /**
     *Handling of clicking on the "about" button
     */
    fun onAboutItemSelected() {
        notesView?.openAboutScreen()
    }

    /**
     * Handling of clicking on the "share" button
     *
     * @param text notes text
     */
    fun onShareBtnClicked(text: String) {
        if (text.isEmpty()) {
            notesView?.shareDataFailed()
        } else {
            notesView?.shareNotes(text)
        }
    }

    /**
     * Method to destroy lifecycle of NotesActivity
     */
    fun onDestroy() {
        notesView = null
    }

    private fun onEmptyError(error: String) {
        notesView?.getDataFailed(error)
    }

    private fun onSuccess(text: String) {
        notesView?.addNotesTextView(text)
    }
}