package com.example.androidmaster.androidExercises

/**
 * Por lo general, la pantalla del teléfono se enciende y se apaga cuando se presiona el botón de encendido.
 * En cambio, si un teléfono plegable está plegado,
 * su pantalla interna principal no se enciende cuando se presiona el botón de encendido.
 */

/**
 * En el código inicial que se proporciona en el siguiente fragmento de código,
 * escribe una clase FoldablePhone que se herede de la clase Phone. Debe contener lo siguiente:
 *
 * Una propiedad que indique si el teléfono está plegado
 * Un comportamiento de función switchOn() diferente del de la clase Phone para que solo encienda la pantalla cuando el teléfono no esté plegado
 * Métodos para cambiar el estado de plegado
 */

/**
 * RESULT:
 * The phone is folded!
 * Unable to turn on the phone! It's folded!
 */

fun main(){
    val phone = Phone()
    val foldablePhone = FoldablePhone()
    foldablePhone.fold()
    foldablePhone.switchOn()
}

open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    open fun switchOff() {
        isScreenLightOn = false
    }

    open fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}
class FoldablePhone(var isFolded : Boolean = false) : Phone() {

    override fun switchOn() {
        if (isFolded == true){
            super.switchOn()
            println("The phone is starting...")
        }else{
            println("Unable to turn on the phone! It's folded!")
        }
    }

    override fun switchOff(){
        if (!isFolded){
            super.switchOff()
            println("The phone is turning off...")
        }else{
            println("Unable to turn off the phone! It's folded!")
        }
    }

    fun fold(){
        isFolded = true
        println("The phone is folded!")
    }

    fun unFold(){
        isFolded = false
        println("The phone is unfolded!")
    }
}

//IT is completed!!!!!! Let's go to the next exercise.