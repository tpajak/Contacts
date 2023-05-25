package validator

interface Validator {
    fun isValid(value: String) : Boolean
}