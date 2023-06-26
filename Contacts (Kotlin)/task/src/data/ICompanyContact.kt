package contacts.data

import contacts.domain.OperationStatus

interface ICompanyContact : IContact {
    fun updateCompanyName(id: Int, newName: String): OperationStatus
    fun updateCompanyAddress(id: Int, newAddress: String): OperationStatus
}