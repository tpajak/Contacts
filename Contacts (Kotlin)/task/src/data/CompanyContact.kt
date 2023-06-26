package contacts.data

import contacts.domain.OperationStatus
import data.Contact

data class CompanyContact(
    private var _organisationName: String,
    private var _address: String,
    override var phoneNumber: String
) : Contact(phoneNumber) {
    override var type: String = "isCompany"
    var organisationName: String
        get() {
            return if (_organisationName != "") _organisationName else ""
        }
        set(value) {
            _organisationName = value
            updateTimeLastEdit()
        }

    var address: String
        get() {
            return if (_address != "") _address else ""
        }
        set(value) {
            _address = value
            updateTimeLastEdit()
        }

    override fun add(contact: Contact): OperationStatus {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): OperationStatus {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): Contact {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "$organisationName"
//        + " ---- DEBUG: Addr: $address, Pho: ${this.phoneNumber}"
    }
}