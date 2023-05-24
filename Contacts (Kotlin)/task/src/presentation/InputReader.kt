package presentation

class InputReader {
    companion object {
        fun readUserInput(): List<String> {
            return readlnOrNull()?.split(" ") ?: ("-1" as List<String>)
        }
    }
}