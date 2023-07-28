package contacts.data

import contacts.data.CompanyContact
import contacts.data.PersonContact
import contacts.domain.OperationStatus
import data.Contact


interface ContactRepository {
    fun createContact(contact: Contact): Boolean
    fun getContact(id: Int): Contact
    fun updatePersonContactName(id: Int, newName: String)
    fun updateCompanyContactName(id: Int, newName: String)
    fun updatePersonContactSurname(id: Int, newSurname: String)
    fun updatePersonContactGender(id: Int, newGender: String)
    fun updatePersonContactBirth(id: Int, newBirth: String)
    fun updatePersonContactPhoneNumber(id: Int, newPhoneNumber: String)
    fun updateCompanyContactAddress(id: Int, newAddress: String)
    fun deleteContact(id: Int): Boolean
    fun listContacts(searchTerm: String = ""): List<Contact>
    fun countContacts(): Int
}