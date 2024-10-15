package com.example.androidmaster.sintaxis

fun main(){
    var name:String = "Android Developer"
    var name1:String = "Android Developer"
    var name2:String = "Android Developer"
    var name3:String = "Android Developer"


    //Position 0-6
    //Size (Number of Elements) 7
    val weekDays = arrayOf("Monday", "Tuesday", "Wendsday", "Thirsday", "Friday", "Saturday", "Sunday")

    //Modifying values
    weekDays[0] = "Not Monday"              //weekDays.get(0) = weekDays.first()

    //println(weekDays[0])

    //println(weekDays.size)

    //Sizes
    if(weekDays.size >= 8){
        //println(weekDays[7])
    }else{
        //println("No more values inside the array")
    }

    //Arrays loops
    for(position in weekDays.indices){
        //println(weekDays[position])
    }

    for((position, value) in weekDays.withIndex()){
        //println("The position $position contains the value $value")
    }

    for(weekDay in weekDays){
        println("Now is $weekDay")
    }

    weekDays.forEach { println(it) }
}