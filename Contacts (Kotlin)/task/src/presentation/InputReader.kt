package presentation

class InputReader {
    companion object {
        fun readUserInput(useSplit: Boolean = true): List<String> {
            return if (useSplit) {
                readlnOrNull()?.split(" ") ?: ("-1" as List<String>)
            } else {
                listOf(readlnOrNull() ?: "-1")
            }
        }
    }
}