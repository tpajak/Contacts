package presentation

import contacts.data.CompanyContact
import contacts.data.PersonContact
import contacts.data.validation.Birthdate
import contacts.data.validation.Gender
import contacts.data.validation.Genders
import contacts.domain.OperationStatus
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
                message.printMessage("Enter action (add, remove, edit, count, info, exit):")
                simpleCommand = readUserAction(contactUseCase)
                result = simpleCommand!!.execute()

            } while (result != "exit")

        }

        fun readUserAction(contactUseCase: ContactUseCase): Command {
            val userInput = InputReader.readUserInput()
            val commandResult = UserActionResult.UserAction(userInput[0])

            when (commandResult.command) {
                "add" -> {
                    message.printMessage("Enter the type (person, organization):")
                    val contactType = InputReader.readUserInput().first()
                    if (contactType == "person") {
                        return SimpleCommand {
                            message.printMessage("Enter the name:")
                            val name = InputReader.readUserInput().first()
                            message.printMessage("Enter the surname:")
                            val surname = InputReader.readUserInput().first()
                            message.printMessage("Enter the birth date:")
                            val birthDate = InputReader.readUserInput().first()
                            if (!Birthdate().isValid(birthDate)) {
                                message.printMessage("Bad birth date!", lineBreak = true)
                            }
                            message.printMessage("Enter the gender (${Genders.M}, ${Genders.F}):")
                            val gender = InputReader.readUserInput().first()
                            if (!Gender().isValid(gender)) {
                                message.printMessage("Bad gender!", lineBreak = true)
                            }
                            message.printMessage("Enter the number:")
                            val phoneNumber = InputReader.readUserInput(splitInput = false).first()
                            if (contactUseCase.createContact(
                                    PersonContact(
                                        name,
                                        surname,
                                        birthDate,
                                        gender,
                                        phoneNumber,
                                    )
                                ) == OperationStatus.SUCCESS
                            ) {
                                message.printMessage("The record added.", lineBreak = true)
                                message.printSeparator()
                            } else {
                                message.printMessage("Something went wrong!")
                            }
                            return@SimpleCommand "addPerson"
                        }
                    } else if (contactType == "organization") {
                        return SimpleCommand {
                            message.printMessage("Enter the organization name:")
                            val organisationName = InputReader.readUserInput(splitInput = false).first()
                            message.printMessage("Enter the address:")
                            val address = InputReader.readUserInput(splitInput = false).first()
                            message.printMessage("Enter the number:")
                            val phoneNumber = InputReader.readUserInput(splitInput = false).first()
                            if (contactUseCase.createContact(
                                    CompanyContact(
                                        organisationName,
                                        address,
                                        phoneNumber
                                    )
                                ) == OperationStatus.SUCCESS
                            ) {
                                message.printMessage("The record added.", lineBreak = true)
                                message.printSeparator()
                            } else {
                                message.printMessage("Something went wrong!")
                            }
                            return@SimpleCommand "addOrganisation"
                        }
                    } else {
                        return SimpleCommand {
                            message.printMessage("Unknown contact type!", lineBreak = true)
                            message.printSeparator()
                            return@SimpleCommand "error"
                        }
                    }
                }

                "remove" -> {
                    return SimpleCommand {
                        if (contactUseCase.listContacts().isEmpty()) {
                            message.printMessage("No records to remove!")
                        } else {
                            message.printContactsList(contactUseCase.listContacts())
                            message.printMessage("Select a record:")
                            val recordToRemove = InputReader.readUserInput().first().toInt()
                            when (contactUseCase.deleteContact(recordToRemove)) {
                                OperationStatus.SUCCESS -> {
                                    message.printMessage("The record removed!")
                                    message.printSeparator()
                                }
                                OperationStatus.EMPTY_LIST -> {
                                    message.printMessage("No records to remove!")
                                    message.printSeparator()
                                }
                                OperationStatus.FAILURE -> message.printMessage("Something went wrong when removing the record!")
                            }
                        }
                        return@SimpleCommand "remove"
                    }
                }

                "edit" -> {
                    return SimpleCommand {
                        if (contactUseCase.listContacts().isEmpty()) {
                            message.printMessage("No records to edit!")
                        } else {
                            message.printContactsList(contactUseCase.listContacts())
                            message.printMessage("Select a record:")
                            val recordToEdit = InputReader.readUserInput().first().toInt()
                            if (contactUseCase.getContact(recordToEdit) is PersonContact) {
                                message.printMessage("Select a field (name, surname, birth, gender, number):")
                                val filedToEdit = InputReader.readUserInput().first()
                                when (filedToEdit) {
                                    "name" -> {
                                        message.printMessage("Enter the name:")
                                        val newName = InputReader.readUserInput().first()
                                        when (contactUseCase.updateContactName(recordToEdit, newName)) {
                                            OperationStatus.SUCCESS -> {
                                                message.printMessage("The record updated!", lineBreak = true)
                                                message.printSeparator()
                                            }
                                            OperationStatus.EMPTY_LIST -> {
                                                message.printMessage("No records to edit!", lineBreak = true)
                                                message.printSeparator()
                                            }
                                            OperationStatus.FAILURE -> message.printMessage("Something went wrong when removing the record!")
                                        }

                                    }

                                    "surname" -> {
                                        message.printMessage("Enter the surname:")
                                        val newSurname = InputReader.readUserInput().first()
                                        when (contactUseCase.updateContactSurname(recordToEdit, newSurname)) {
                                            OperationStatus.SUCCESS -> {
                                                message.printMessage("The record updated!")
                                                message.printSeparator()
                                            }
                                            OperationStatus.EMPTY_LIST -> {
                                                message.printMessage("No records to edit!")
                                                message.printSeparator()
                                            }
                                            OperationStatus.FAILURE -> message.printMessage("Something went wrong when removing the record!")
                                        }
                                    }

                                    "birth" -> {
                                        message.printMessage("Enter the birth:")
                                        val newSurname = InputReader.readUserInput().first()
                                        when (contactUseCase.updateContactBirth(recordToEdit, newSurname)) {
                                            OperationStatus.SUCCESS -> {
                                                message.printMessage("The record updated!")
                                                message.printSeparator()
                                            }
                                            OperationStatus.EMPTY_LIST -> {
                                                message.printMessage("No records to edit!")
                                                message.printSeparator()
                                            }
                                            OperationStatus.FAILURE -> message.printMessage("Something went wrong when removing the record!")
                                        }
                                    }

                                    "gender" -> {
                                        message.printMessage("Enter the gender:")
                                        val newSurname = InputReader.readUserInput().first()
                                        when (contactUseCase.updateContactGender(recordToEdit, newSurname)) {
                                            OperationStatus.SUCCESS -> {
                                                message.printMessage("The record updated!", lineBreak = true)
                                                message.printSeparator()
                                            }
                                            OperationStatus.EMPTY_LIST -> {
                                                message.printMessage("No records to edit!", lineBreak = true)
                                                message.printSeparator()
                                            }
                                            OperationStatus.FAILURE -> message.printMessage("Something went wrong when removing the record!")
                                        }
                                    }

                                    "number" -> {
                                        message.printMessage("Enter the number:")
                                        val newNumber = InputReader.readUserInput(splitInput = false).first()
                                        when (contactUseCase.updateContactPhoneNumber(recordToEdit, newNumber)) {
                                            OperationStatus.SUCCESS -> {
                                                message.printMessage("The record updated!", lineBreak = true)
                                                message.printSeparator()
                                            }
                                            OperationStatus.EMPTY_LIST -> {
                                                message.printMessage("No records to edit!", lineBreak = true)
                                                message.printSeparator()
                                            }
                                            OperationStatus.FAILURE -> message.printMessage("Something went wrong when removing the record!")
                                        }
                                    }
                                }

                            } else if (contactUseCase.getContact(recordToEdit) is CompanyContact) {
                                message.printMessage("Select a field (address, number):")
                                val filedToEdit = InputReader.readUserInput().first()
                                when (filedToEdit) {

                                    "address" -> {
                                        message.printMessage("Enter address:")
                                        val newAddress = InputReader.readUserInput(splitInput = false).first()
                                        when (contactUseCase.updateContactAddress(recordToEdit, newAddress)) {
                                            OperationStatus.SUCCESS -> {
                                                message.printMessage("The record updated!", lineBreak = true)
                                                message.printSeparator()
                                            }
                                            OperationStatus.EMPTY_LIST -> {
                                                message.printMessage("No records to edit!", lineBreak = true)
                                                message.printSeparator()
                                            }
                                            OperationStatus.FAILURE -> message.printMessage("Something went wrong when removing the record!")
                                        }
                                    }

                                    "number" -> {
                                        message.printMessage("Enter the number:")
                                        val newNumber = InputReader.readUserInput(splitInput = false).first()
                                        when (contactUseCase.updateContactPhoneNumber(recordToEdit, newNumber)) {
                                            OperationStatus.SUCCESS -> {
                                                message.printMessage("The record updated!", lineBreak = true)
                                                message.printSeparator()
                                            }
                                            OperationStatus.EMPTY_LIST -> {
                                                message.printMessage("No records to edit!", lineBreak = true)
                                                message.printSeparator()
                                            }
                                            OperationStatus.FAILURE -> message.printMessage("Something went wrong when removing the record!")
                                        }
                                    }
                                }
                            } else {
                                    message.printMessage("Unknown contact type!")
                                    return@SimpleCommand "error"
                            }
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

                "info" -> {
                    return SimpleCommand {
                        if (contactUseCase.listContacts().isEmpty()) {
                            message.printMessage("No records to list!")
                        } else {
                            message.printContactsList(contactUseCase.listContacts())
                            message.printMessage("Enter index to show info:")
                            val recordToShow = InputReader.readUserInput().first().toInt()
                            message.printContactDetails(contactUseCase.getContact(recordToShow))
                        }
                        return@SimpleCommand "info"
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