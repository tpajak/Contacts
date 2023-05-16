package contacts.UseCase

import contacts.Data.InMemoryContactRepository
import Data.Contact

class ContactUseCase(
    private val inMemoryContactRepository: InMemoryContactRepository
) : ContactRepository {

    override fun createContact(contact: Contact) : Boolean {
        return inMemoryContactRepository.createContact(contact)
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