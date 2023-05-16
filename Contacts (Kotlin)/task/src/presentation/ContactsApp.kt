package contacts.Presentation

import data.Contact
import contacts.Data.InMemoryContactRepository
import contacts.UseCase.ContactUseCase

class ContactsApp {

    companion object {
        fun start() {
            val message = PrintToConsole()

            message.printMessage("Enter the name of the person:")
            val name = InputReader.readUserInput().toString()
            message.printMessage("Enter the surname of the person:")
            val surname = InputReader.readUserInput().toString()
            message.printMessage("Enter the number:")
            val phoneNumber = InputReader.readUserInput().toString()

            val contactUseCase = ContactUseCase(InMemoryContactRepository())
                .createContact(Contact(name, surname, phoneNumber))
            if (contactUseCase) {
                message.printMessage("A record created!\nA Phone Book with a single record created!")
            } else {
                message.printMessage("Something went wrong!")
            }
        }
    }
}