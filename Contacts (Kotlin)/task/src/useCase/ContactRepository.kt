package useCase

import contacts.domain.OperationStatus
import data.Contact


interface ContactRepository {
    fun createContact(contact: Contact) : Boolean
    fun getContact(id: Int) : Contact
    fun updateContact(id: Int) : Boolean
    fun deleteContact(id: Int) : OperationStatus
    fun listContacts() : List<Contact>
    fun countContacts() : Int
}