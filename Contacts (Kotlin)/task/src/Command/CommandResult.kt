package contacts.Command

sealed class CommandResult(var command: String) {
    class Command(command: String) : CommandResult(command = command)
}