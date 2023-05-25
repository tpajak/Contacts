package data

import contacts.domain.OperationStatus
import useCase.ContactRepository

private val contacts = mutableListOf<Contact>()

class InMemoryContactRepository : ContactRepository {
    override fun createContact(contact: Contact) : Boolean {
        return contacts.add(contact)
    }

    override fun getContact(id: Int): Contact {
        TODO("Not yet implemented")
    }

    override fun updateContact(id: Int): String {
        TODO("Not yet implemented")
    }

    override fun deleteContact(id: Int): OperationStatus {
        if (countContacts() <= 0) {
            return OperationStatus.EMPTY_LIST
        } else {
            try {
                contacts.removeAt(id - 1)
                return OperationStatus.SUCCESS
            } catch (e: IndexOutOfBoundsException) {
                return OperationStatus.FAILURE
            }
        }
    }

    override fun listContacts(): List<Contact> {
        return contacts
    }

    override fun countContacts(): Int {
        return contacts.count()
    }


}