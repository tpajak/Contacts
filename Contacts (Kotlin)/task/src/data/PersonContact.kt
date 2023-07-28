package contacts.data

import contacts.data.validation.Birthdate
import contacts.data.validation.Gender
import data.Contact
import validation.PhoneNumber

data class PersonContact(
    private var _name: String,
    private var _surname: String,
    private var _birthdate: String,
    private var _gender: String,
    override var phoneNumber: String,
) : Contact(phoneNumber), IContact {

    override var type: String = "isPerson"

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

    var name: String
        get() {
            return _name
        }
        set(value) {
            _name = value
            updateTimeLastEdit()
        }

    var surname: String
        get() {
            return _surname
        }
        set(value) {
            _surname = value
            updateTimeLastEdit()
        }

    var birthdate: String
        get() {
            return _birthdate
        }
        set(value) {
            if (!Birthdate().isValid(value)) {
                _birthdate = "[no data]"
            } else {
                _birthdate = value
            }
            updateTimeLastEdit()
        }

    var gender: String
        get() {
            return _gender
        }
        set(value) {
            if (!Gender().isValid(value)) {
                _gender = "[no data]"
            } else {
                _gender = value
            }
            updateTimeLastEdit()
        }

    override fun toString(): String {
        return "$name $surname"
//        + " ---- DEBUG: Bdate: $birthdate, Gen: $gender, Pho: $phoneNumber, Type: $type"
    }
}