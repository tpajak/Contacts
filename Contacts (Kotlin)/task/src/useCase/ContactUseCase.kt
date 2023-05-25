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

    override fun updateContact(id: Int): String {
        TODO("Not yet implemented")
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