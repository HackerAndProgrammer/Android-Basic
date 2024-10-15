package com.example.androidmaster.sintaxis

fun main(){
    var name:String? = "Android Development"

    //println(name!!.get(3))                               This doesn't work
    //print(name?.get(3))                                  This does.
    print(name?.get(3) ?: "It's a null value")
}