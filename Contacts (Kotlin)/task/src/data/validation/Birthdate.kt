package contacts.data.validation

import validation.Validate

class Birthdate : Validate {
    override fun isValid(value: String): Boolean {
        //"21.01.2023"
        val pattern = "^\\d{1,2}\\.[0-1]\\d\\.\\d{4}"
        return value.matches(Regex(pattern))
    }
}