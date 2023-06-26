package contacts.data

import contacts.data.validation.Birthdate
import contacts.data.validation.Gender
import contacts.domain.OperationStatus
import data.Contact
import validation.PhoneNumber

data class PersonContact(
    private val _name: String,
    private val _surname: String,
    private var _birthdate: String,
    private var _gender: String,
    override var phoneNumber: String,
) : Contact(phoneNumber), IContact, IPersonContact {

    override var type: String = "isPerson"
        get() {
            return if (field != "") field else "[type not defined]"
        }

    init {
        if (!PhoneNumber().isValid(phoneNumber)) {
            phoneNumber = "[no number]"
        } else {
            phoneNumber
        }

        if (!Birthdate().isValid(_birthdate)) {
            _birthdate = "[no data]"
        } else {
            _birthdate
        }

        if (!Gender().isValid(_gender)) {
            _gender = "[no data]"
        } else {
            _gender
        }
    }

    var name: String = _name
        get() {
            return if (field != "") field else ""
        }
        set(value) {
            field = value
            updateTimeLastEdit()
        }

    var surname: String = _surname
        get() {
            return if (field != "") field else ""
        }
        set(value) {
            field = value
            updateTimeLastEdit()
        }

    var birthdate: String = _birthdate
        get() {
            return if (field != "") field else ""
        }
        set(value) {
            if (!Birthdate().isValid(value)) {
                field = "[no data]"
            } else {
                field = value
            }
            updateTimeLastEdit()
        }

    var gender: String = _gender
        get() {
            return if (field != "") field else ""
        }
        set(value) {
            if (!Gender().isValid(value)) {
                field = "[no data]"
            } else {
                field = value
            }
            updateTimeLastEdit()
        }

//    override var phoneNumber: String = _phoneNumber
//        get() {
//            return if (field != "") field else ""
//        }
//        set(value) {
//            if (!PhoneNumber().isValid(value)) {
//                field = "[no number]"
//            } else {
//                field = value
//            }
//            updateTimeLastEdit()
//        }

    override fun add(contact: Contact): OperationStatus {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): OperationStatus {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): Contact {
        TODO("Not yet implemented")
    }

    override fun updatePersonName(id: Int, newName: String): OperationStatus {
        TODO("Not yet implemented")
    }

    override fun updatePersonSurname(id: Int, newSurname: String): OperationStatus {
        TODO("Not yet implemented")
    }

    override fun updatePersonBirthdate(id: Int, newBirth: String): OperationStatus {
        TODO("Not yet implemented")
    }

    override fun updatePersonGender(id: Int, newGender: String): OperationStatus {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "$name $surname"
//        + " ---- DEBUG: Bdate: $birthdate, Gen: $gender, Pho: $phoneNumber, Type: $type"
    }
}