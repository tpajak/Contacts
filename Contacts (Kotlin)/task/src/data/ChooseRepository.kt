package contacts.data

class ChooseRepository {
    companion object {
        fun chooseRepository(source: Array<String>): ContactStorage {
            if (source.isEmpty()) {
                return InMemoryContactStorage()
            } else {
                return InFileContactStorage(source[1])
            }

        }
    }
}

