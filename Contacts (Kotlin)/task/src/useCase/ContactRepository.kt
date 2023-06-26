package contacts.useCase

import contacts.domain.OperationStatus
import data.Contact


interface ContactRepository {
    fun createContact(contact: Contact) : OperationStatus
    fun getContact(id: Int) : Contact
    fun updateContactName(id: Int, newName: String) : OperationStatus
    fun updateContactSurname(id: Int, newSurname: String) : OperationStatus
    fun updateContactGender(id: Int, newGender: String) : OperationStatus
    fun updateContactBirth(id: Int, newBirth: String) : OperationStatus
    fun updateContactPhoneNumber(id: Int, newPhoneNumber: String): OperationStatus
    fun updateContactAddress(id: Int, newAddress: String): OperationStatus
    fun deleteContact(id: Int) : OperationStatus
    fun listContacts() : List<Contact>
    fun countContacts() : Int
}