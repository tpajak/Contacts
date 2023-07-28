package contacts.data

import data.Contact
import java.io.File

//private val contacts: MutableList<Contact> = mutableListOf()


class InFileContactStorage(fileName: Any) : ContactStorage {
    private var contacts: MutableList<Contact> = mutableListOf()
    private val file = File("Contacts (Kotlin)/task/src/data/$fileName")

    init {
        if (!file.exists()) {
            file.createNewFile()
        } else {
            loadContactsToFile()
        }
    }

    override fun add(contact: Contact): Boolean {
        contacts.add(contact)
        return saveContactsToFile()
    }

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
        saveContactsToFile()
    }

    override fun updateCompanyContactName(id: Int, newName: String) {
        val contact = contacts[id] as CompanyContact
        contact.organisationName = newName
        saveContactsToFile()
    }

    override fun updatePersonContactSurname(id: Int, newName: String) {
        val contact = contacts[id] as PersonContact
        contact.surname = newName
        saveContactsToFile()
    }

    override fun updatePersonContactGender(id: Int, newGender: String) {
        val contact = contacts[id] as PersonContact
        contact.gender = newGender
        saveContactsToFile()
    }

    override fun updatePersonContactBirth(id: Int, newBirth: String) {
        val contact = contacts[id] as PersonContact
        contact.birthdate = newBirth
        saveContactsToFile()
    }

    override fun updatePersonContactPhoneNumber(id: Int, newPhoneNumber: String) {
        val contact = contacts[id] as PersonContact
        contact.phoneNumber = newPhoneNumber
        saveContactsToFile()
    }

    override fun updateCompanyContactAddress(id: Int, newAddress: String) {
        val contact = contacts[id] as CompanyContact
        contact.address = newAddress
        saveContactsToFile()
    }

    override fun delete(i: Int): Boolean {
        contacts.remove(get(i))
        return saveContactsToFile()
    }


    private fun saveContactsToFile(): Boolean {
        try {
            file.writeText(Serialiser().toJson(contacts))
            return true
        } catch (e: Exception) {
            println(e.message)
            return false
        }
    }

    private fun loadContactsToFile(): Boolean {
        try {
            contacts = Serialiser().fromJson(file)
            return true
        } catch (e: Exception) {
            println(e.message)
            return false
        }
    }

}
