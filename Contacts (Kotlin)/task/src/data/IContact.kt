package contacts.data

import contacts.domain.OperationStatus
import data.Contact

interface IContact {
    fun add(contact: Contact): OperationStatus
    fun delete(id: Int): OperationStatus
    fun get(id: Int): Contact
}