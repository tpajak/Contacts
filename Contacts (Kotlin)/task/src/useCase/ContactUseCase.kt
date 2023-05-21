package useCase

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

    override fun deleteContact(id: Int): String {
        TODO("Not yet implemented")
    }

    override fun listContact(): List<Contact> {
        TODO("Not yet implemented")
    }

    override fun countContacts(): Int {
        return contactRepository.countContacts()
    }
}