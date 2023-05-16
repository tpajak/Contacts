package contacts.repository.validator

interface Validator {
    fun isValid(value: String) : Boolean
}