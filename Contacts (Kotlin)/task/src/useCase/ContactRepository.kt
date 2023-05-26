package useCase

import contacts.domain.OperationStatus
import data.Contact


interface ContactRepository {
    fun createContact(contact: Contact) : Boolean
    fun getContact(id: Int) : Contact
    fun updateContactName(id: Int, newName: String) : OperationStatus
    fun updateContactSurname(id: Int, newSurname: String) : OperationStatus
    fun updateContactPhoneNumber(id: Int, newPhoneNumber: String) : OperationStatus
    fun deleteContact(id: Int) : OperationStatus
    fun listContacts() : List<Contact>
    fun countContacts() : Int
}