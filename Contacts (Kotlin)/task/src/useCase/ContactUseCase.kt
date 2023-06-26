package useCase

import contacts.useCase.ContactRepository
import contacts.domain.OperationStatus
import data.Contact
import data.InMemoryContactRepository

class ContactUseCase(
    private val contactRepository: InMemoryContactRepository
) : ContactRepository {

    override fun createContact(contact: Contact) : OperationStatus {
        return contactRepository.createContact(contact)
    }

    override fun getContact(id: Int): Contact {
        return contactRepository.getContact(id)
    }

    override fun updateContactName(id: Int, newName: String): OperationStatus {
        return contactRepository.updateContactName(id, newName)
    }

    override fun updateContactSurname(id: Int, newSurname: String): OperationStatus {
        return contactRepository.updateContactSurname(id, newSurname)
    }

    override fun updateContactGender(id: Int, newGender: String): OperationStatus {
        return contactRepository.updatePersonGender(id, newGender)
    }

    override fun updateContactBirth(id: Int, newBirth: String): OperationStatus {
        return contactRepository.updateContactBirth(id, newBirth)
    }

    override fun updateContactPhoneNumber(id: Int, newPhoneNumber: String): OperationStatus {
        return contactRepository.updateContactPhoneNumber(id, newPhoneNumber)
    }

    override fun updateContactAddress(id: Int, newAddress: String): OperationStatus {
        return contactRepository.updateContactAddress(id, newAddress)
    }

    override fun deleteContact(id: Int): OperationStatus {
        return contactRepository.deleteContact(id)
    }

    override fun listContacts(): List<Contact> {
        return contactRepository.listContacts()
    }

    override fun countContacts(): Int {
        return contactRepository.countContacts()
    }
}