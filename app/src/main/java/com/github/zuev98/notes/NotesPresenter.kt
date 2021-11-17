package com.github.zuev98.notes

class NotesPresenter(private var notesView: NotesContract.View?,
                     private val notesModel: NotesContract.Model) :
    NotesContract.Presenter, NotesContract.Model.OnFinishedListener {

    override fun onButtonClick(heading: String, note: String) {
        notesModel.check(heading, note, this)
    }

    override fun onDestroy() {
        notesView = null
    }

    override fun onEmptyError(error: String) {
        notesView?.getDataFailed(error)
    }

    override fun onSuccess(text: String) {
        notesView?.addNotesTextView(text)
    }
}