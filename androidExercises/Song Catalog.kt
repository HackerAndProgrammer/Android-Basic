package com.example.androidmaster.androidExercises

fun main() {
    class Song(val title: String, val artist: String, val year: Int, val playCount: Int) {

        fun isPopular() {
            if (playCount < 1000) {
                val isPopular = false
            }
        }

        fun printSong() {
            println("$title, played by $artist in $year")
        }

    }

    val mySong =
        Song(title = "New York, New York", artist = "Frank Sinatra", year = 1979, playCount = 1500)
    mySong.printSong()
}