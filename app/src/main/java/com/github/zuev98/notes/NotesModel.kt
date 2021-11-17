package com.github.zuev98.notes

class NotesModel : NotesContract.Model {
    override fun check(heading: String,
                       note: String,
                       listener: NotesContract.Model.OnFinishedListener) {
        when {
            heading.isBlank() && note.isBlank() -> listener.onEmptyError("Fill in the fields!")
            heading.isBlank() -> listener.onEmptyError("Fill in the heading!")
            note.isBlank() -> listener.onEmptyError("Fill in the note!")
            else -> listener.onSuccess("$heading:\n$note\n\n")
        }
    }
}