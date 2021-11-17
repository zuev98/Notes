package com.github.zuev98.notes

interface NotesContract {
    interface View {
        //method to add note on the TextView
        fun addNotesTextView(text: String)

        //method to get error data
        fun getDataFailed(error: String)
    }

    interface Presenter {
        //method to be called when the button is clicked
        fun onButtonClick(heading: String, note: String)

        //method to destroy lifecycle of NotesActivity
        fun onDestroy()
    }

    interface Model {
        interface OnFinishedListener{
            fun onEmptyError(error: String)
            fun onSuccess(text: String)
        }

        //method to check fields for emptiness
        fun check(heading: String, note: String, listener: OnFinishedListener)
    }
}