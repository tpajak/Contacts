package contacts.data

import com.squareup.moshi.*
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import data.Contact
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Serialiser {

    fun toJson (contacts: MutableList<Contact>): String {
        return baseAdapter.toJson(contacts)
    }

    fun fromJson (file: File) : MutableList<Contact> {
        return baseAdapter.fromJson(file.readText())!!
    }


    private val type = Types.newParameterizedType(MutableList::class.java, Contact::class.java)

    private val adapterFactory = PolymorphicJsonAdapterFactory
        .of(Contact::class.java, "base")
        .withSubtype(PersonContact::class.java, "person")
        .withSubtype(CompanyContact::class.java, "organisation")
    private val moshi = Moshi.Builder()
        .add(adapterFactory)
        .add(LocalDateTimeAdapter)
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val baseAdapter = moshi.adapter<MutableList<Contact>>(type)


}

object LocalDateTimeAdapter : JsonAdapter<LocalDateTime>() {
    private val formatter = DateTimeFormatter.ISO_DATE_TIME


    @FromJson
    override fun fromJson(reader: JsonReader): LocalDateTime? {
        return try {
            val dateAsString = reader.nextString()
            synchronized(formatter) {
                LocalDateTime.parse(dateAsString, formatter)
            }
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: LocalDateTime?) {
        if (value != null) {
            synchronized(formatter) {
                writer.value(value.toString())
            }
        }
    }

}