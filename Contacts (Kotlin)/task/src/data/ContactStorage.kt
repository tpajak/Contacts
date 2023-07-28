package contacts.data

import data.Contact

interface ContactStorage {
    fun add(contact: Contact): Boolean
    fun get(i: Int): Contact
    fun count(): Int
    fun getAll(): List<Contact>
    fun getSearchResults(searchTerm: String): List<Contact>
    fun updatePersonContactName(id: Int, newName: String)
    fun updateCompanyContactName(id: Int, newName: String)
    fun updatePersonContactSurname(id: Int, newName: String)
    fun updatePersonContactGender(id: Int, newGender: String)
    fun updatePersonContactBirth(id: Int, newBirth: String)
    fun updatePersonContactPhoneNumber(id: Int, newPhoneNumber: String)
    fun updateCompanyContactAddress(id: Int, newAddress: String)
    fun delete(i: Int): Boolean

}