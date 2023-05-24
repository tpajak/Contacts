package data

data class Contact(
    private val _name:String,
    private val _surname:String,
    private val _phoneNumber:String,
) {
    val name: String
        get() {
            return if (_name != "") _name else ""
        }

    val surname: String
        get() {
            return if (_surname != "") _surname else ""
        }

    val phoneNumber: String
        get() {
            return if (_phoneNumber != "") _phoneNumber else "[no number]"
        }

    override fun toString(): String {
        return "$name $surname, $phoneNumber"
    }


}