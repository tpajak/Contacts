package operations

sealed class UserActionResult (val command: String) {
    class UserAction(command: String ) : UserActionResult (command = command)
}