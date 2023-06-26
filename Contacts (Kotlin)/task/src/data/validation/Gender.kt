package contacts.data.validation

import contacts.data.validation.Genders.*
import validation.Validate

class Gender : Validate {
    val pattern = "^[$M,$F]"
    override fun isValid(value: String): Boolean {
        return value.matches(Regex(pattern))
        //TODO: Check if real validation is needed
    }
}

enum class Genders {
    M,
    F,
}