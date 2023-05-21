package operations

sealed class UserActionResult (val command: String) {
    class UserAction(command: String ) : UserActionResult (command = command)
    class Add(command: String ) : UserActionResult (command = command)
    class Remove(command: String ) : UserActionResult (command = command)
    class Edit(command: String ) : UserActionResult (command = command)
    class Count(command: String ) : UserActionResult (command = command)
    class List(command: String ) : UserActionResult (command = command)
    class Exit(command: String ) : UserActionResult (command = command)
}