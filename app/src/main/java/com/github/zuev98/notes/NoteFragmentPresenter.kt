package com.github.zuev98.notes

/**
 * Presenter для [NoteFragment]
 *
 * @param noteFragmentView view, к которой можно получить доступ через интерфейс
 */
class NoteFragmentPresenter(private var noteFragmentView: NoteFragmentView?) {
    /**
     * Обработка нажатия на кнопку "Share"
     *
     * @param heading заголовок заметки
     * @param text текст заметки
     */
    fun onShareBtnClicked(heading: String, text: String) {
        if (heading.isEmpty() || text.isEmpty()) {
            noteFragmentView?.shareDataFailed()
        } else {
            noteFragmentView?.shareNote(heading, text)
        }
    }

    /**
     * Обрабтока нажатия на кнопку "Save"
     *
     * @param heading заголовок заметки
     * @param note текст заметки
     */
    fun onSaveBtnClicked(heading: String, note: String) {
        when {
            heading.isBlank() && note.isBlank() -> onEmptyError("Fill in the fields!")
            heading.isBlank() -> onEmptyError("Fill in the heading!")
            note.isBlank() -> onEmptyError("Fill in the note!")
            else -> onSuccess()
        }
    }

    private fun onEmptyError(error: String) {
        noteFragmentView?.getDataFailed(error)
    }

    private fun onSuccess() {
        noteFragmentView?.addNotesTextView("Note saved")
    }
}