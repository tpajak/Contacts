package presentation

import contacts.data.CompanyContact
import contacts.data.PersonContact
import data.Contact

class PrintToConsole : Print {
    override fun printMessage(message: String, lineBreak: Boolean) {
        return if (lineBreak) {
            println(message)
        } else {
            print(message)
        }
    }

    override fun printContactsList(list: List<Contact>) {
        for ((index, contact) in list.withIndex()) {
            println("${index + 1}. ${contact}")
        }
    }

    override fun printContactDetails(contact: Contact) {
        when (contact) {
            is PersonContact -> {
                println(
                    "Name: ${contact.name}\n" +
                            "Surname: ${contact.surname}\n" +
                            "Birth date: ${contact.birthdate}\n" +
                            "Gender: ${contact.gender}\n" +
                            "Number: ${contact.phoneNumber}\n" +
                            "Time created: ${contact.creationTime}\n" +
                            "Time last edit: ${contact.lastEditTime}"
                )
            }
            is CompanyContact -> {
                println(
                    "Organization name: ${contact.organisationName}\n" +
                            "Address: ${contact.address}\n" +
                            "Number: ${contact.phoneNumber}\n" +
                            "Time created: ${contact.creationTime}\n" +
                            "Time last edit: ${contact.lastEditTime}"
                )
            }
            else -> {
                printMessage("Unknown Contact type", lineBreak = true)
            }
        }
        printSeparator()
    }

    override fun printSeparator(separator: String) {
        println(separator)
    }
}