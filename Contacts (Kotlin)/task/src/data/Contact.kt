package data

data class Contact(
    private val _name:String,
    private val _surname:String,
    private val _phoneNumber:String,
) {
    private val name: String = _name
        get() {
            return if (field != "") field else ""
        }

    private val surname: String = _surname
        get() {
            return if (field != "") field else ""
        }

    private val phoneNumber: String = _phoneNumber
        get() {
            return if (field != "") field else "[no number]"
        }

    override fun toString(): String {
        return "$name $surname, $phoneNumber"
    }

}