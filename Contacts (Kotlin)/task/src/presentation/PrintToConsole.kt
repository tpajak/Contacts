package presentation

import data.Contact

class PrintToConsole : Print {
    override fun printMessage(message: String) {
        return println(message)
    }

    override fun printContactsList(list: List<Contact>) {
        for ((index, contact) in list.withIndex()) {
            println("${index + 1}. ${contact}")
        }
    }
}