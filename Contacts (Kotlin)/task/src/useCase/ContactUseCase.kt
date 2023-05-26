package useCase

import contacts.domain.OperationStatus
import data.Contact
import data.InMemoryContactRepository

class ContactUseCase(
    private val contactRepository: InMemoryContactRepository
) : ContactRepository {

    override fun createContact(contact: Contact) : Boolean {
        return contactRepository.createContact(contact)
    }

    override fun getContact(id: Int): Contact {
        TODO("Not yet implemented")
    }

    override fun updateContactName(id: Int, newName: String): OperationStatus {
        return contactRepository.updateContactName(id, newName)
    }

    override fun updateContactSurname(id: Int, newSurname: String): OperationStatus {
        return contactRepository.updateContactSurname(id, newSurname)
    }

    override fun updateContactPhoneNumber(id: Int, newPhoneNumber: String): OperationStatus {
        return contactRepository.updateContactPhoneNumber(id, newPhoneNumber)
    }

    override fun deleteContact(id: Int): OperationStatus {
        return contactRepository.deleteContact(id)
    }

    override fun listContacts(): List<Contact> {
        return contactRepository.listContacts()
    }

    override fun countContacts(): Int {
        return contactRepository.countContacts()
    }
}