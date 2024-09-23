package com.example.androidmaster.androidExercises

fun main() {
    val amanda = Person("Amanda", 33, "play tennis", null)
    val bruce = Person("Bruce", 28, "climb", amanda)

    amanda.showProfile()
    bruce.showProfile()
}

class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        if (referrer == null) {
            println("Name: $name")
            println("Age: $age")
            println("Likes to $hobby. Does not have a referrer ")
            println("")
        } else {
            println("Name: $name")
            println("Age: $age")
            println("Likes to $hobby. Has a referrer named ${referrer.name}, who likes to ${referrer.hobby}")
        }
    }
}