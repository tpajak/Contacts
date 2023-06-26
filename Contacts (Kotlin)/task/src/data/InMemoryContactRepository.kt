package data

import contacts.data.*
import contacts.domain.OperationStatus
import contacts.useCase.ContactRepository


private val contacts: MutableList<Contact> = mutableListOf()

class InMemoryContactRepository : ContactRepository, IPersonContact, ICompanyContact, IContact {
    override fun createContact(contact: Contact): OperationStatus {
        return add(contact)
    }

    override fun getContact(id: Int): Contact {
        return get(id)
    }

    override fun updateContactName(id: Int, newName: String): OperationStatus {
        return when (contacts[id - 1]) {
            is PersonContact -> updatePersonName(id, newName)
            is CompanyContact -> updateCompanyName(id, newName)
            else -> OperationStatus.FAILURE
        }
    }

    override fun updateContactSurname(id: Int, newSurname: String): OperationStatus {
        return when (contacts[id - 1]) {
            is PersonContact -> updatePersonSurname(id, newSurname)
            else -> OperationStatus.FAILURE
        }
    }

    override fun updateContactGender(id: Int, newGender: String): OperationStatus {
        return when (contacts[id - 1]) {
            is PersonContact -> updatePersonSurname(id, newGender)
            else -> OperationStatus.FAILURE
        }
    }

    override fun updateContactBirth(id: Int, newBirth: String): OperationStatus {
        return when (contacts[id - 1]) {
            is PersonContact -> updatePersonSurname(id, newBirth)
            else -> OperationStatus.FAILURE
        }
    }

    override fun updateContactPhoneNumber(id: Int, newPhoneNumber: String): OperationStatus {
        try {
            contacts[id - 1].phoneNumber = newPhoneNumber
            return OperationStatus.SUCCESS
        } catch (e: IndexOutOfBoundsException) {
            return OperationStatus.FAILURE
        }
    }

    override fun updateContactAddress(id: Int, newAddress: String): OperationStatus {
        return when (contacts[id - 1]) {
            is CompanyContact -> updateCompanyAddress(id, newAddress)
            else -> OperationStatus.FAILURE
        }
    }

    override fun deleteContact(id: Int): OperationStatus {
        return delete(id)
    }

    override fun updateCompanyName(id: Int, newName: String): OperationStatus {
        return try {
            val contact = contacts[id - 1]
            contact as CompanyContact
            contact.organisationName = newName
            OperationStatus.SUCCESS
        } catch (e: IndexOutOfBoundsException) {
            OperationStatus.FAILURE
        }
    }

    override fun updateCompanyAddress(id: Int, newAddress: String): OperationStatus {
        return try {
            val contact = contacts[id - 1]
            contact as CompanyContact
            contact.address = newAddress
            OperationStatus.SUCCESS
        } catch (e: IndexOutOfBoundsException) {
            OperationStatus.FAILURE
        }
    }

    override fun updatePersonName(id: Int, newName: String): OperationStatus {
        return try {
            val contact = contacts[id - 1]
            contact as PersonContact
            contact.name = newName
            OperationStatus.SUCCESS
        } catch (e: IndexOutOfBoundsException) {
            OperationStatus.FAILURE
        }
    }

    override fun updatePersonSurname(id: Int, newSurname: String): OperationStatus {
        return try {
            val contact = contacts[id - 1]
            contact as PersonContact
            contact.surname = newSurname
            OperationStatus.SUCCESS
        } catch (e: IndexOutOfBoundsException) {
            OperationStatus.FAILURE
        }
    }

    override fun updatePersonBirthdate(id: Int, newBirth: String): OperationStatus {
        return try {
            val contact = contacts[id - 1]
            contact as PersonContact
            contact.birthdate = newBirth
            OperationStatus.SUCCESS
        } catch (e: IndexOutOfBoundsException) {
            OperationStatus.FAILURE
        }
    }

    override fun updatePersonGender(id: Int, newGender: String): OperationStatus {
        return try {
            val contact = contacts[id - 1]
            contact as PersonContact
            contact.gender = newGender
            OperationStatus.SUCCESS
        } catch (e: IndexOutOfBoundsException) {
            OperationStatus.FAILURE
        }
    }

    override fun listContacts(): List<Contact> {
        return contacts
    }

    override fun countContacts(): Int {
        return contacts.count()
    }

    override fun add(contact: Contact): OperationStatus {
        return if (contact.type == "isPerson" || contact.type == "isCompany") {
            contacts.add(contact)
            OperationStatus.SUCCESS
        } else {
            OperationStatus.FAILURE
        }
    }

    override fun delete(id: Int): OperationStatus {
        if (countContacts() <= 0) {
            return OperationStatus.EMPTY_LIST
        } else {
            return try {
                contacts.removeAt(id - 1)
                OperationStatus.SUCCESS
            } catch (e: IndexOutOfBoundsException) {
                OperationStatus.FAILURE
            }
        }
    }

    override fun get(id: Int): Contact {
        return contacts[id - 1]
    }

}