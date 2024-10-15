package com.example.androidmaster.sintaxis

fun main() {
    nestedIf("bird")
}

fun multipleIfOr() {
    var pet = "cat"
    var isHappy = false

    if ((pet == "cat" && isHappy) || (pet == "dog" && isHappy)) {                  //AND and OR
        println("It's a happy cat, or a happy dog")
    } else if (pet == "cat" && isHappy == false) {
        println("It's a cat, but he is not happy. Let's buy some good food for him.")
    }
}

fun multipleIf() {
    var age = 18
    var parentPermission = true
    var iWant = true

    if (age >= 18 && parentPermission && iWant) {                  //AND
        print("You can drink beer!")
    }
}

fun intIf() {

    var age = 18

    if (age >= 18) {
        println("You can dring beeer. (But please do not exagerate)")
    } else {
        println("You still can't drink. But don't worry, you can always drink Coke. (But do not exagerate)")
    }
}

fun ifBoolean() {
    var imStudying: Boolean = true

    //If there's nothing, == true
    //If ! at the beggining, == false (Just in this case)
    if (!imStudying) {
        println("mmmm... You should be studying...")
    }
}

fun nestedIf(animal: String) {
    if (animal == "cat") {
        println("It's a cat!!!!!")
    } else if (animal == "dog") {
        println("It is a dog... Sorry, that was all I could find.")
    } else if (animal == "bird") {
        println("It's a bird!! I hope you don't have a meat-eater pet in the house.")
    } else if (animal == "fish") {
        println("It is a fish!!! I hope you don't have a cat in the house.")
    } else {
        println("It is not a common pet.")
    }
}

fun basicIf() {
    val name = "Julian"

    if (name == "Julian") {
        //This is what is going to do if the condition is True
        println("Hey there!I see you got the right name, $name")
    } else {
        //This is what we are going to do if the condition is False
        println("Hey there! You know, you got the wrong name, buddy. The correct name is Julian.")
    }
}