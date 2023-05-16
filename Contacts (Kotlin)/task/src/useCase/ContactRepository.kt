package contacts.UseCase

import data.Contact

interface ContactRepository {
    fun createContact(contact: Contact) : Boolean
    fun getContact(id: Int) : Contact
    fun updateContact(id: Int) : String
    fun deleteContact(id: Int) : String
    fun listContact() : List<Contact>
}