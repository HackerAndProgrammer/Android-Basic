package com.example.androidmaster.sintaxis

fun main() {
    mutableList()
}

fun mutableList() {
    val weekDays: MutableList<String> =
        mutableListOf("Monday", "Tuesday", "Wendsday", "Thirsday", "Friday", "Saturday", "Sunday")
    //println(weekDays)

    weekDays.add(
        0,
        "Androiday"
    )                       //the .add() function puts the added value at the end of the list, always. But, we can specify Kotlin the position of the added value.

    //println(weekDays)
    if(weekDays.isEmpty()) {
        //I am not going to print anything because the list is empty.
    } else {
        weekDays.forEach { weekDay -> println(weekDay) }
    }
                      //=
    if (weekDays.isNotEmpty()) {
        weekDays.forEach { println(it) }                            //= weekDays.forEach { x -> println(x) }
    }

    weekDays.last()

    //weekDays.forEach { weekDay ->println(weekDay) } = for(weekDay in weekDays){...}
}

fun inmutableList() {
    val readOnly: List<String> =
        listOf("Monday", "Tuesday", "Wendsday", "Thirsday", "Friday", "Saturday", "Sunday")

    //println(readOnly.size)
    //println(readOnly)
    //println(readOnly[0])
    //println(readOnly.last())
    //println(readOnly.first()) = println(readOnly[0] = println(readOnly.get(0))


    //Filter
    //val example = readOnly.filter { it.contains("x") }
    //println(example)


    //Iterate
    readOnly.forEach { println(it) }                               //= readOnly.forEach { x -> println(x) }

}