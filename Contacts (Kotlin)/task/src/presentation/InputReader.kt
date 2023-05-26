package presentation

class InputReader {
    companion object {
        fun readUserInput(splitInput: Boolean = true): List<String> {
            return if (splitInput) {
                readlnOrNull()?.split(" ") ?: ("-1" as List<String>)
            } else {
                listOf(readlnOrNull() ?: "-1")
            }
        }
    }
}