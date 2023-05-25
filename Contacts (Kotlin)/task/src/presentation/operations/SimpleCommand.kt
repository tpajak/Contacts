package operations

class SimpleCommand(private val command: () -> String) : Command {
    override fun execute() = command.invoke()
}