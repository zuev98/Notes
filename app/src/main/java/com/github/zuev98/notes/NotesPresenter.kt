package com.github.zuev98.notes

class NotesPresenter(private var notesView: View?) : Presenter {

    override fun onButtonClick(heading: String, note: String) {
        when {
            heading.isBlank() && note.isBlank() -> onEmptyError("Fill in the fields!")
            heading.isBlank() -> onEmptyError("Fill in the heading!")
            note.isBlank() -> onEmptyError("Fill in the note!")
            else -> onSuccess("$heading:\n$note\n\n")
        }
    }

    override fun onDestroy() {
        notesView = null
    }

    private fun onEmptyError(error: String) {
        notesView?.getDataFailed(error)
    }

    private fun onSuccess(text: String) {
        notesView?.addNotesTextView(text)
    }
}