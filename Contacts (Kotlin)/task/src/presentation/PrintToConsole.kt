package presentation

import data.Contact

class PrintToConsole : Print {
    override fun printMessage(message: String, lineBreak: Boolean) {
        return if (lineBreak) {
            println(message)
        } else {
            print(message)
        }
    }

    override fun printContactsList(list: List<Contact>) {
        for ((index, contact) in list.withIndex()) {
            println("${index + 1}. ${contact}")
        }
    }
}