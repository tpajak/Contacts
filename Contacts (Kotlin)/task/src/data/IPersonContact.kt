package contacts.data

import contacts.domain.OperationStatus

interface IPersonContact : IContact {
    fun updatePersonName(id: Int, newName: String): OperationStatus
    fun updatePersonSurname(id: Int, newSurname: String): OperationStatus
    fun updatePersonBirthdate(id: Int, newBirth: String): OperationStatus
    fun updatePersonGender(id: Int, newGender: String): OperationStatus
}