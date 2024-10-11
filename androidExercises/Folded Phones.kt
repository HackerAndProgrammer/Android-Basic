package com.example.androidmaster.androidExercises

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
        if (!isFolded){
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
