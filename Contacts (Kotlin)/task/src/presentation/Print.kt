package presentation

import data.Contact

interface Print {
    fun printMessage(message: String, lineBreak: Boolean = false)
    fun printContactsList(list: List<Contact>)
}