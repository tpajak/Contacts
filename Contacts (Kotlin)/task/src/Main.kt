package contacts

import contacts.data.Serialiser
import presentation.ContactsApp

fun main(args: Array<String>) {
    println(args.joinToString().replace(",",""))
    ContactsApp.start(args)
}