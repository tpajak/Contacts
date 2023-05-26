package data

import validation.PhoneNumber

data class Contact(
    private val _name:String,
    private val _surname:String,
    private var _phoneNumber:String,
) {
    init {
        if (!PhoneNumber().isValid(_phoneNumber)) {
            _phoneNumber = "[no number]"
        } else {
            _phoneNumber
        }
    }

    var name: String = _name
        get() {
            return if (field != "") field else ""
        }
        set(value) {
            field = value
        }

    var surname: String = _surname
        get() {
            return if (field != "") field else ""
        }
        set(value) {
            field = value
        }

    var phoneNumber: String = _phoneNumber
        get() {
            return if (field != "") field else "[no number]"
        }
        set(value) {
            if (!PhoneNumber().isValid(value)) {
                field = "[no number]"
            } else {
                field = value
            }
        }

    override fun toString(): String {
        return "$name $surname, $phoneNumber"
    }

}