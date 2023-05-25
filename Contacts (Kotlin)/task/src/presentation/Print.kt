package presentation

import data.Contact

interface Print {
    fun printMessage(message: String)
    fun printContactsList(list: List<Contact>)
}