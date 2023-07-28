package presentation

import contacts.data.*
import contacts.data.validation.Birthdate
import contacts.data.validation.Gender
import contacts.data.validation.Genders
import contacts.domain.OperationStatus
import data.ContactRepositoryImpl
import operations.Command
import operations.SimpleCommand
import operations.UserActionResult
import useCase.ContactUseCase

class ContactsApp {

    companion object {
        //        var storage: ContactStorage = InMemoryContactStorage()
        var result: String? = null
        val message = PrintToConsole()
        var simpleCommand: Command? = null

        fun start(args: Array<String>) {

            val storage = ChooseRepository.chooseRepository(args)
            val contactUseCase: ContactUseCase = ContactUseCase(ContactRepositoryImpl(storage))


            do {
                message.printMessage("[menu] Enter action (add, list, search, count, exit):")
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
                            addPerson(contactUseCase)
                            return@SimpleCommand "addPerson"
                        }
                    } else if (contactType == "organization") {
                        return SimpleCommand {
                            addOrganization(contactUseCase)
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
                            removeContact(contactUseCase, recordToRemove)
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
                            message.printMessage("[list] Enter action ([number], back):")
                            val recordToEdit = InputReader.readUserInput().first()
                            if (recordToEdit.contains("back")) {
                                return@SimpleCommand "back"
                            } else {
                                val recordToEdit = recordToEdit.toInt()
                                if (contactUseCase.getContact(recordToEdit) is PersonContact) {
                                    editPerson(contactUseCase, recordToEdit)
                                } else if (contactUseCase.getContact(recordToEdit) is CompanyContact) {
                                    editCompany(contactUseCase, recordToEdit)
                                } else {
                                    message.printMessage("Unknown contact type!")
                                    return@SimpleCommand "error"
                                }
                            }
                        }
                        return@SimpleCommand "edit"
                    }
                }

                "search" -> {
                    return SimpleCommand {
                        do {
                            message.printMessage("Enter search query:")
                            val searchTerm = InputReader.readUserInput().first()
                            val searchResult = contactUseCase.listContacts(searchTerm)
                            message.printMessage("Found ${searchResult.size} results:", lineBreak = true)
                            message.printContactsList(searchResult)
                            message.printSeparator()
                            message.printMessage("[search] Enter action ([number], back, again):")
                            val userInput = InputReader.readUserInput().first()
                            val isDigit = "\\d".toRegex()
                            if (userInput.matches(isDigit)) {
                                message.printContactDetails(contactUseCase.getContact(userInput.toInt()))
                                message.printMessage("[record] Enter action (edit, delete, menu):")
                                val userInputSubmenu = InputReader.readUserInput().first()
                                if (userInputSubmenu == "delete") {
                                    removeContact(contactUseCase, userInput.toInt())
                                } else if (userInputSubmenu == "edit") {
                                    val recordToEdit = userInput.toInt()
                                    if (contactUseCase.getContact(recordToEdit) is PersonContact) {
                                        editPerson(contactUseCase, recordToEdit)
                                    } else if (contactUseCase.getContact(recordToEdit) is CompanyContact) {
                                        editCompany(contactUseCase, recordToEdit)
                                    } else {
                                        message.printMessage("Unknown contact type!")
                                        return@SimpleCommand "error"
                                    }
                                    return@SimpleCommand "search"
                                } else if (userInputSubmenu == "menu") {
                                    return@SimpleCommand "record"
                                }
                            }
                        } while (userInput != "back")
                        return@SimpleCommand "search"
                    }
                }


                "count" -> {
                    return SimpleCommand {
                        val records = contactUseCase.countContacts()
                        message.printMessage("The Phone Book has $records records.", lineBreak = true)
                        message.printSeparator()
                        return@SimpleCommand "count"
                    }
                }

                "list" -> {
                    val isDigit = "\\d".toRegex()

                    return SimpleCommand {
                        if (contactUseCase.listContacts().isEmpty()) {
                            message.printMessage("No records to list!", lineBreak = true)
                            message.printSeparator()
                        } else {
                            message.printContactsList(contactUseCase.listContacts())
                            message.printSeparator()

                            message.printMessage("[list] Enter action ([number], back):")
                            val listUserInput = InputReader.readUserInput().first()
                            if (listUserInput.matches(isDigit)) {
                                message.printContactDetails(contactUseCase.getContact(listUserInput.toInt()))
                                message.printMessage("[record] Enter action (edit, delete, menu):")
                                val userInputSubmenu = InputReader.readUserInput().first()
                                if (userInputSubmenu == "delete") {
                                    removeContact(contactUseCase, listUserInput.toInt())
                                } else if (userInputSubmenu == "edit") {
                                    val recordToEdit = listUserInput.toInt()
                                    if (contactUseCase.getContact(recordToEdit) is PersonContact) {
                                        editPerson(contactUseCase, recordToEdit)
                                    } else if (contactUseCase.getContact(recordToEdit) is CompanyContact) {
                                        editCompany(contactUseCase, recordToEdit)
                                    } else {
                                        message.printMessage("Unknown contact type!")
                                        return@SimpleCommand "error"
                                    }
                                    return@SimpleCommand "search"
                                } else if (userInputSubmenu == "menu") {
                                    return@SimpleCommand "list"
                                }
                            } else {
                                return@SimpleCommand "list"
                            }


                            message.printMessage("[list] Enter action ([number], back):")
                            val userInput = InputReader.readUserInput().first()
                            if (userInput.matches(isDigit)) {
                                message.printContactDetails(contactUseCase.getContact(userInput.toInt()))
                                message.printMessage("[record] Enter action (edit, delete, menu):")
                                val userInputSubmenu = InputReader.readUserInput().first()
                                if (userInputSubmenu == "delete") {
                                    removeContact(contactUseCase, userInput.toInt())
                                } else if (userInputSubmenu == "edit") {
                                    val recordToEdit = userInput.toInt()
                                    if (contactUseCase.getContact(recordToEdit) is PersonContact) {
                                        editPerson(contactUseCase, recordToEdit)
                                    } else if (contactUseCase.getContact(recordToEdit) is CompanyContact) {
                                        editCompany(contactUseCase, recordToEdit)
                                    } else {
                                        message.printMessage("Unknown contact type!")
                                        return@SimpleCommand "error"
                                    }
                                    return@SimpleCommand "search"
                                } else if (userInputSubmenu == "menu") {
                                    return@SimpleCommand "list"
                                }
                            } else {
                                return@SimpleCommand "list"
                            }

                        }
                        return@SimpleCommand "list"
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

        private fun editCompany(contactUseCase: ContactUseCase, recordToEdit: Int) {
            message.printMessage("Select a field (name, address, number):")
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

                "name" -> {
                    message.printMessage("Enter name:")
                    val newName = InputReader.readUserInput(splitInput = false).first()
                    when (contactUseCase.updateContactName(recordToEdit, newName)) {
                        OperationStatus.SUCCESS -> {
                            message.printMessage("Saved", lineBreak = true)
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
        }

        private fun editPerson(contactUseCase: ContactUseCase, recordToEdit: Int) {
            message.printMessage("Select a field (name, surname, birth, gender, number):")
            val filedToEdit = InputReader.readUserInput().first()
            when (filedToEdit) {
                "name" -> {
                    message.printMessage("Enter name:")
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
                    val newBirthdate = InputReader.readUserInput().first()
                    when (contactUseCase.updateContactBirth(recordToEdit, newBirthdate)) {
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
                    val newGender = InputReader.readUserInput().first()
                    when (contactUseCase.updateContactGender(recordToEdit, newGender)) {
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
        }

        private fun removeContact(contactUseCase: ContactUseCase, recordToRemove: Int) {
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

        private fun addOrganization(contactUseCase: ContactUseCase) {
            message.printMessage("Enter the organization name:")
            val organisationName = InputReader.readUserInput(splitInput = false).first()
            message.printMessage("Enter the address:")
            val address = InputReader.readUserInput(splitInput = false).first()
            message.printMessage("Enter the number:")
            val phoneNumber = InputReader.readUserInput(splitInput = false).first()
            if (contactUseCase.createContact(
                    CompanyContact(
                        organisationName, address, phoneNumber
                    )
                ) == OperationStatus.SUCCESS
            ) {
                message.printMessage("The record added.", lineBreak = true)
                message.printSeparator()
            } else {
                message.printMessage("Something went wrong!")
            }
        }

        private fun addPerson(contactUseCase: ContactUseCase) {
            message.printMessage("Enter name:")
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
        }
    }
}