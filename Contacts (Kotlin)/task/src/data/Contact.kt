package data

import contacts.data.IContact
import validation.PhoneNumber
import java.time.LocalDateTime

abstract class Contact(
    private var _phoneNumber: String
) : IContact {
    abstract var type: String
    private var _id: Int? = null
    private lateinit var _creationTime: LocalDateTime
    private lateinit var _lastEditTime: LocalDateTime

    init {
        updateCreatedTime()
    }

    open var phoneNumber: String
        get() {
            return if (_phoneNumber != "") _phoneNumber else ""
        }
        set(value) {
            _phoneNumber = if (!PhoneNumber().isValid(value)) {
                "[no number]"
            } else {
                value
            }
            updateTimeLastEdit()
        }

    var lastEditTime: LocalDateTime
        get() = _lastEditTime
        set(value) {
            _lastEditTime = value
        }

    var creationTime: LocalDateTime
        get() = _creationTime
        set(value) {
            _creationTime = value
        }

    fun updateTimeLastEdit () {
        lastEditTime = LocalDateTime.now()
    }

    private fun updateCreatedTime () {
        val currentTime = LocalDateTime.now()
        creationTime = currentTime
        lastEditTime = currentTime
    }

}