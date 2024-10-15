package com.example.androidmaster.sintaxis

fun main() {
    getSemester(4)
}

fun result(value: Any) {                                                //Any. Android expects a value of any kind.
    when (value) {
        is Int -> value + value
        is String -> println(value)
        is Boolean -> if (value) print("Value is equal to True") else println("Value is equal to False")
    }
}

fun getSemester(month: Int) = when (month) {
         in 1..6 -> "First Semester"
         in 7..12 -> "Second Semester"                             //Ranges. "In"
         !in 1..12 -> "This is NOT a valid semester"
         else -> "Nothing."
     }



fun getTrimester(month: Int) {
    when (month) {
        1, 2, 3 -> println("First Trimester")
        4, 5, 6 -> println("Second Trimester")
        7, 8, 9 -> println("Third Trimester")
        10, 11, 12 -> println("Forth Trimester")
    }
}

fun getMonth(month: Int) {
    when (month) {
        1 -> println("January")
        2 -> println("February")
        3 -> println("March")
        4 -> println("April")
        5 -> println("May")
        6 -> println("June")
        7 -> println("July")
        8 -> println("August")
        9 -> println("September")
        10 -> println("October")
        11 -> println("November")
        12 -> println("December")
        else -> println("This month doesn't exist")
    }
}

fun getMonthNovember(month: Int) {
    when (month) {
        1 -> println("January")
        2 -> println("February")
        3 -> println("March")
        4 -> println("April")
        5 -> println("May")
        6 -> println("June")
        7 -> println("July")
        8 -> println("August")
        9 -> println("September")
        10 -> println("October")
        11 -> {
            println("November")
            println("November")
            println("November")                                        //This is for printing a lot of elements inside the condition (If 11 == True)
            println("November")
            println("November")
        }

        12 -> println("December")
        else -> println("This month doesn't exist")
    }
}