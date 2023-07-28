package useCase

import contacts.data.CompanyContact
import contacts.data.PersonContact
import contacts.data.ContactRepository
import contacts.domain.OperationStatus
import data.Contact

class ContactUseCase(
    private val contactRepository: ContactRepository,
) {

    fun createContact(contact: Contact): OperationStatus {
        return if (contactRepository.createContact(contact)) {
            OperationStatus.SUCCESS
        } else {
            OperationStatus.FAILURE
        }
    }

    fun getContact(id: Int): Contact {
        return contactRepository.getContact(id)
    }

    fun updateContactName(id: Int, newName: String): OperationStatus {
        return try {
            when (contactRepository.getContact(id)) {
                is PersonContact -> {
                    contactRepository.updatePersonContactName(id, newName)
                    return OperationStatus.SUCCESS
                }

                is CompanyContact -> {
                    contactRepository.updateCompanyContactName(id, newName)
                    return OperationStatus.SUCCESS
                }

                else -> {
                    return OperationStatus.FAILURE
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            return OperationStatus.FAILURE
        }
    }

    fun updateContactSurname(id: Int, newSurname: String): OperationStatus {
        return try {
            if (contactRepository.getContact(id) is PersonContact) {
                contactRepository.updatePersonContactSurname(id, newSurname)
                OperationStatus.SUCCESS
            } else {
                OperationStatus.FAILURE
            }
        } catch (e: IndexOutOfBoundsException) {
            return OperationStatus.FAILURE
        }
    }

    fun updateContactGender(id: Int, newGender: String): OperationStatus {
        return try {
            if (contactRepository.getContact(id) is PersonContact) {
                contactRepository.updatePersonContactGender(id, newGender)
                OperationStatus.SUCCESS
            } else {
                OperationStatus.FAILURE
            }
        } catch (e: IndexOutOfBoundsException) {
            return OperationStatus.FAILURE
        }
    }

    fun updateContactBirth(id: Int, newBirth: String): OperationStatus {
        return try {
            if (contactRepository.getContact(id) is PersonContact) {
                contactRepository.updatePersonContactBirth(id, newBirth)
                OperationStatus.SUCCESS
            } else {
                OperationStatus.FAILURE
            }
        } catch (e: IndexOutOfBoundsException) {
            return OperationStatus.FAILURE
        }
    }

    fun updateContactPhoneNumber(id: Int, newPhoneNumber: String): OperationStatus {
        return try {
            if (contactRepository.getContact(id) is PersonContact) {
                contactRepository.updatePersonContactPhoneNumber(id, newPhoneNumber)
                OperationStatus.SUCCESS
            } else {
                OperationStatus.FAILURE
            }
        } catch (e: IndexOutOfBoundsException) {
            return OperationStatus.FAILURE
        }
    }

    fun updateContactAddress(id: Int, newAddress: String): OperationStatus {
        return try {
            if (contactRepository.getContact(id) is CompanyContact) {
                contactRepository.updateCompanyContactAddress(id, newAddress)
                OperationStatus.SUCCESS
            } else {
                OperationStatus.FAILURE
            }
        } catch (e: IndexOutOfBoundsException) {
            return OperationStatus.FAILURE
        }
    }

    fun deleteContact(id: Int): OperationStatus {
        return try {
            if (contactRepository.deleteContact(id)) {
                OperationStatus.SUCCESS
            } else {
                OperationStatus.FAILURE
            }
        } catch (e: IndexOutOfBoundsException) {
            return OperationStatus.FAILURE
        }
    }

    fun listContacts(searchTerm: String = ""): List<Contact> {
        return contactRepository.listContacts(searchTerm)
    }

    fun countContacts(): Int {
        return contactRepository.countContacts()
    }
}