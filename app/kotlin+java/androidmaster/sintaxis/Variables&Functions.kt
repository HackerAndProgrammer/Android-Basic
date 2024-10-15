package com.example.androidmaster.sintaxis

val age: Int = 14                     //It's a Global Variable. This means that we can access to it from wherever we want.

fun main() {
    showMyName()
    showMyAge(14)
    add(25, 76)
    val mySubtract = subtract(10, 5)
    print(mySubtract)
}

fun showMyName() {
    println("My name is Julian")
}

fun showMyAge(currentAge: Int) {
    println("I'm $currentAge years old.")
}

fun add(firstNumber: Int, secondNumber: Int) {
    println(firstNumber + secondNumber)
}

fun subtract(firstNumber: Int, secondNumber: Int): Int {
    return firstNumber - secondNumber
}

//fun subtractCool(firsNumber: Int, secondNumber: Int):Int = firsNumber - secondNumber
//=
//fun subtractCool(firsNumber: Int, secondNumber: Int) = firsNumber - secondNumber


fun alphaNumericalVariables() {
    /**
     *Alphanumerical Variables
     */

    //Char This is useful for storing ONE, JUST ONE CHARacter in the variable. It can be a number(Inside the quotes), It can be a letter, etc.
    val charExample: Char = 'e'
    val charExample1: Char = '2'
    val charExample3: Char = '@'

    //String This is useful for text. That's it.
    val stringExample: String = "Kotlin"
    val stringExample1 = "Apps Dev"
    val stringExample2 = "4"
    val stringExample3 = "23"

    var stringConcatenada: String = "Hola"
    stringConcatenada = "Hola estoy estudiando: $stringExample , y tengo $age años"
    println(stringConcatenada)
    val example123: String = age.toString()
}


fun booleanVariables() {
    /**
     *Boolean Variables This is useful for Asking the status of x object, or whatever. It has only 2 potential values: true - false
     */
    val boolExample: Boolean = true
    val boolExample1: Boolean = false
    val boolExample2 = true
}

fun numericalVariables() {
    /**
     *  Numerical Variables
     */

    //Int This is useful for very small numbers.
    val age: Int = 30
    var age2 = 30

    //println(age2)   //Out = 30. We also must now that "val" it's a const variable (wich means it can't change), and "var" it's a variable name. It's mutable.
    age2 = 22
    //print(age2)   //Out = 29.
    //Long This is useful for very big numbers
    val example: Long = 30000

    //Float This is useful for decimal numbers (We need to use an "f" at the end of the number so Android knows it's a float value)
    val floatExample: Float = 30.5f

    //Double This is useful for a lot of decimal numbers.
    val doubleExample: Double = 241.92843

    println("Add:")
    println(age + age2)
    println("Subtract:")
    println(age - age2)

    println("Multiply:")                     //Basic math operations
    println(age * age2)

    println("Split:")
    println(age / age2)

    println("Module:")
    println(age % age2)

    var exampleAdd = age + example
}

