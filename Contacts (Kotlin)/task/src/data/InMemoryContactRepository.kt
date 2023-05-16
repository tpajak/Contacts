package contacts.Data

import contacts.UseCase.ContactRepository
import data.Contact

val contacts = mutableListOf<Contact>()

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

    override fun deleteContact(id: Int): String {
        TODO("Not yet implemented")
    }

    override fun listContact(): List<Contact> {
        TODO("Not yet implemented")
    }


}