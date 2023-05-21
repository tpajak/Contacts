package contacts

fun returnIndex(sentence: String): Int {
    var position: Int = 0
    for (index in 0..sentence.length - 3) {
        if (sentence[index] == 't' &&
            sentence[index + 1] == 'h' &&
            sentence[index + 2] == 'e') {
            position = index
            break
        } else {
            position = -1
        }
    }
    return position
}

    fun main2() {


        val input = readln().lowercase()
        println(returnIndex(input))


//    //**************************************************************

//    val noNumbers = readln().toInt()
//    val numbers = IntArray(noNumbers) { readln().toInt() }
//    val numberToCount = readln().toInt()
//
//    var counter = 0
//    for (number in numbers) {
//        if (numbers[number] === numberToCount) {
//            counter++
//        }
//    }
//    println(counter)

//    //**************************************************************
//    val arraySize = readln().toInt()
//    val dataSet = IntArray(arraySize) { readln().toInt() }
//    val numbersToCheck: List<String> = readln().split(" ")
//
////    for (number in numbersToCheck) println(number
//
//    var areNextToEachOther = ""
//    for (element in 0..dataSet.size - 2) {
//        if (dataSet[element] == numbersToCheck[0].toInt() && dataSet[element + 1] == numbersToCheck[1].toInt() ||
//            dataSet[element] == numbersToCheck[1].toInt() && dataSet[element + 1] == numbersToCheck[0].toInt()
//        ) {
//            areNextToEachOther = "NO"
//            break
//        } else {
//            areNextToEachOther = "YES"
//        }
//
//    }
//    println(areNextToEachOther)


//    //**************************************************************
//val input = MutableList<Int>(3) { readln().toInt() }
//
//for (element in input) {
//    println(isOdd(element))
//}
//    //**************************************************************
//    val number = 5
//    val number = readln().toInt()
//
//    println(if (number % 2 == 0) "ODD" else "EVEN")

//    //**************************************************************

//    val high = DangerLevel.HIGH
//    val medium = DangerLevel.MEDIUM
//
//    println(high.getLevel() > medium.getLevel()) // true


//    //**************************************************************
//
//    data class Client(val name: String, val age: Int, val gender: String) {
//    }
//
//    val bob = Client("Bob", 29, "Male")
//    println(bob.name) // Bob
//    println(bob.component1()) // Bob
//    println(bob.age)  // 29
//    println(bob.component2()) // 29
////    println(bob.gender) // Male
////    println(bob.component3()) // Male
//
//    // destructuring
//    val (name, age, gender) = bob
//    println(name) // Bob
//    println(age)  // 29
//    println(gender) // Male

//**************************************************************

//    class IntegerRepository {
//        private val _list = mutableListOf<Int>()
//        val list: List<Int> get() = _list
//    }
//
//    val repository: IntegerRepository = IntegerRepository()
//    repository.list.add(3) // error -> mutable _list is private and public list is not mutable


//**************************************************************
//    val size = readln().toInt()
//    val array = IntArray(size)
//
//    for (i in 0..array.lastIndex) {
//        array[i] = readln().toInt()
//    }

//    val size = 5
//    val array = arrayOf(1,2,3,4,5)
//
//    val lastElement = array.last()
//
//    for (i in array.size downTo 0) {
//        if (i <= 1) {
//            array[0] = lastElement
//        } else {
//            array[i-1] = array[i - 2]
//        }
//    }
//
//    println(array.joinToString(" "))
//    println("5 1 2 3 4")

    }

    class CoursePactice {
    }

    enum class DangerLevel(private val priority: String) {
        HIGH("3"),
        MEDIUM("2"),
        LOW("1");

        fun getLevel(): String {
            return this.priority
        }
    }

    fun isOdd(number: Int): String {
        return if (number % 2 == 0) "ODD" else "EVEN"
    }