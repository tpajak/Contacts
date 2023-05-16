package contacts.UseCase

import data.Contact

class ContactUseCase(
    private val contactRepository: ContactRepository
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
}