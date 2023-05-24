package presentation

import data.Contact
import data.InMemoryContactRepository
import operations.Command
import operations.SimpleCommand
import operations.UserActionResult
import useCase.ContactUseCase

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
                    return SimpleCommand {
                        message.printMessage("Enter the name:")
                        val name = InputReader.readUserInput().first()
                        message.printMessage("Enter the surname of the person:")
                        val surname = InputReader.readUserInput().first()
                        message.printMessage("Enter the number:")
                        val phoneNumber = InputReader.readUserInput().first()
                        if (contactUseCase.createContact(Contact(name, surname, phoneNumber))) {
                            message.printMessage("The record added")
                        } else {
                            message.printMessage("Something went wrong!")
                        }
                        return@SimpleCommand "add"
                    }
                }

                "edit" -> {
                    return SimpleCommand {
                        if (contactUseCase.listContacts().isEmpty()) {
                            message.printMessage("No records to edit!")
                        } else {
                            message.printContactsList(contactUseCase.listContacts())
                        }
                        return@SimpleCommand "edit"
                    }
                }

                "count" -> {
                    return SimpleCommand {
                        val records = contactUseCase.countContacts()
                        message.printMessage("The Phone Book has $records records.")
                        return@SimpleCommand "count"
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