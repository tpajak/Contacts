package contacts.Presentation

import data.Contact
import contacts.Data.InMemoryContactRepository
import contacts.UseCase.ContactUseCase
import contacts.presentation.operations.Command
import contacts.presentation.operations.SimpleCommand
import contacts.presentation.operations.UserActionResult

class ContactsApp {

    companion object {
        var result: String? = null
        val contactUseCase: ContactUseCase = ContactUseCase(InMemoryContactRepository())
        val message = PrintToConsole()
        var simpleCommand: Command? = null


        fun start() {

            do {
                message.printMessage("Enter action (add, remove, edit, count, list, exit):")
                simpleCommand = readUserAction(contactUseCase)
                result = simpleCommand!!.execute()

            } while (result != "exit")


        }

        fun readUserAction(contactUseCase: ContactUseCase): Command {
            val userInput = InputReader.readUserInput()
            val commandResult = UserActionResult.UserAction(userInput[0])

            when (commandResult.command) {
                "add" -> {
//                        val commandResult = UserActionResult.Add(userInput[0]) //TODO: DO I need to pass userInput param?
                    return SimpleCommand {
                        message.printMessage("Enter the name:")
                        val name = InputReader.readUserInput().toString()
                        message.printMessage("Enter the surname of the person:")
                        val surname = InputReader.readUserInput().toString()
                        message.printMessage("Enter the number:")
                        val phoneNumber = InputReader.readUserInput().toString()
                        if (contactUseCase.createContact(Contact(name, surname, phoneNumber))) {
                            message.printMessage("The record added")
                        } else {
                            message.printMessage("Something went wrong!")
                        }
                        return@SimpleCommand "add"
                    }
                }

                "exit" -> {
                    return SimpleCommand {
                        return@SimpleCommand "exit"
                    }
                }

                else -> {
                    return object : Command {
                        override fun execute(): String {
                            return "No such command."
                        }
                    }
                }
            }

        }
    }
}