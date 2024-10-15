package com.example.androidmaster.androidExercises

/**
 * A menudo, debes completar los perfiles de sitios web en línea que
 * contienen campos obligatorios y no obligatorios.
 * Por ejemplo, puedes agregar tu información personal y un vínculo a
 * otras personas que te refirieron para que registraras tu perfil.
 *
 * Escribe un programa que imprima los detalles del perfil de una persona.
 */

/**
 * RESULT:
 * Name: Amanda
 * Age: 33
 * Likes to play tennis. Doesn't have a referrer.
 *
 * Name: Atiqah
 * Age: 28
 * Likes to climb. Has a referrer named Amanda, who likes to play tennis.
 */

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

//IT is completed!!!!!! Let's go to the next exercise.