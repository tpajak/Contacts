package data

import validator.ValidatorPhoneNumber

data class Contact(
    private val _name:String,
    private val _surname:String,
    private var _phoneNumber:String,
) {
    init {
        if (!ValidatorPhoneNumber().isValid(_phoneNumber)) {
            _phoneNumber = "[no number]"
        } else {
            _phoneNumber
        }
    }

    private val name: String = _name
        get() {
            return if (field != "") field else ""
        }

    private val surname: String = _surname
        get() {
            return if (field != "") field else ""
        }

    var phoneNumber: String = _phoneNumber
        get() {
            return if (field != "") field else "[no number]"
        }
        set(value) {
            if (!ValidatorPhoneNumber().isValid(value)) {
                _phoneNumber = "[no number]"
            } else {
                _phoneNumber
            }
        }

    override fun toString(): String {
        return "$name $surname, $phoneNumber"
    }

}