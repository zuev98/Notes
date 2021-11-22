package com.github.zuev98.notes

interface View {
    //method to add note on the TextView
    fun addNotesTextView(text: String)

    //method to get error data
    fun getDataFailed(error: String)
}