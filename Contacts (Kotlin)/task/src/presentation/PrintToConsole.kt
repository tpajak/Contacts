package contacts.Presentation

class PrintToConsole : Print {
    override fun printMessage(message: String) {
        return println(message)
    }
}