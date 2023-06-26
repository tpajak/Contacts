package contacts.data.validation

import validation.Validate

class Adress : Validate{
    override fun isValid(value: String): Boolean {
        return value.matches(Regex("Street"))
        //TODO: Check if real validation is needed
    }
}