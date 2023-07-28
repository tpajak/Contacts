package contacts.data

import data.Contact

class InMemoryContactStorage : ContactStorage {
    private val contacts: MutableList<Contact> = mutableListOf()

    override fun add(contact: Contact): Boolean = contacts.add(contact)
    override fun get(i: Int): Contact = contacts[i]
    override fun count(): Int = contacts.size
    override fun getAll(): List<Contact> = contacts
    override fun getSearchResults(searchTerm: String): List<Contact> {
        return contacts.filter { contact ->
            when (contact) {
                is PersonContact -> {
                    contact.name.contains(searchTerm, ignoreCase = true) ||
                    contact.surname.contains(searchTerm, ignoreCase = true) ||
                    contact.phoneNumber.contains(searchTerm, ignoreCase = true)
                }
                is CompanyContact -> {
                    contact.organisationName.contains(searchTerm, ignoreCase = true) ||
                    contact.phoneNumber.contains(searchTerm, ignoreCase = true)
                }
                else -> false
            }
        }
    }

    override fun updatePersonContactName(id: Int, newName: String) {
        val contact = contacts[id] as PersonContact
        contact.name = newName
    }

    override fun updateCompanyContactName(id: Int, newName: String) {
        val contact = contacts[id] as CompanyContact
        contact.organisationName = newName
    }

    override fun updatePersonContactSurname(id: Int, newName: String) {
        val contact = contacts[id] as PersonContact
        contact.surname = newName
    }

    override fun updatePersonContactGender(id: Int, newGender: String) {
        val contact = contacts[id] as PersonContact
        contact.gender = newGender
    }

    override fun updatePersonContactBirth(id: Int, newBirth: String) {
        val contact = contacts[id] as PersonContact
        contact.birthdate = newBirth
    }

    override fun updatePersonContactPhoneNumber(id: Int, newPhoneNumber: String) {
        val contact = contacts[id] as PersonContact
        contact.phoneNumber = newPhoneNumber
    }

    override fun updateCompanyContactAddress(id: Int, newAddress: String) {
        val contact = contacts[id] as CompanyContact
        contact.address = newAddress
    }

    override fun delete(i: Int): Boolean = contacts.remove(get(i))

}