package data

import contacts.data.*
import contacts.domain.OperationStatus
import contacts.data.ContactRepository

class ContactRepositoryImpl(private val storage: ContactStorage) : ContactRepository {
    override fun createContact(contact: Contact): Boolean {
        return storage.add(contact)
    }

    override fun getContact(id: Int): Contact {
        return storage.get(id - 1)
    }

    override fun updatePersonContactName(id: Int, newName: String) {
        storage.updatePersonContactName(getContactFromStorage(id), newName)
    }

    override fun updateCompanyContactName(id: Int, newName: String) {
        storage.updateCompanyContactName(getContactFromStorage(id), newName)
    }

    override fun updatePersonContactSurname(id: Int, newSurname: String) {
        storage.updatePersonContactSurname(getContactFromStorage(id), newSurname)
    }

    override fun updatePersonContactGender(id: Int, newGender: String) {
        storage.updatePersonContactGender(getContactFromStorage(id), newGender)
    }

    override fun updatePersonContactBirth(id: Int, newBirth: String) {
        storage.updatePersonContactBirth(getContactFromStorage(id), newBirth)
    }

    override fun updatePersonContactPhoneNumber(id: Int, newPhoneNumber: String) {
        storage.updatePersonContactPhoneNumber(getContactFromStorage(id), newPhoneNumber)
    }

    override fun updateCompanyContactAddress(id: Int, newAddress: String) {
        storage.updateCompanyContactAddress(getContactFromStorage(id), newAddress)
    }

    override fun deleteContact(id: Int): Boolean {
        return storage.delete(getContactFromStorage(id))
    }

    override fun listContacts(searchTerm: String): List<Contact> {
        return if (searchTerm == "") {
            storage.getAll()
        } else {
            storage.getSearchResults(searchTerm)
        }
    }

    override fun countContacts(): Int {
        return storage.count()
    }

    private fun getContactFromStorage(id: Int): Int { return id - 1 }

}