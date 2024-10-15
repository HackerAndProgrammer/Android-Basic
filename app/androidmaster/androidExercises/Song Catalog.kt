package com.example.androidmaster.androidExercises

/**
 * Crea una clase que pueda representar la estructura de una canción.
 * La clase Song debe incluir estos elementos de código:
 *
 * Propiedades para el título, el artista, el año de publicación y el recuento de reproducciones
 * Propiedad que indica si la canción es popular (si el recuento de reproducciones es inferior a 1,000, considera que es poco popular)
 * Un método para imprimir la descripción de una canción en este formato:
 * "[Título], interpretada por [artista], se lanzó en [año de lanzamiento]".
 */

/**
 * RESULT:
 * (Depends on the parameters chosen by you)
 */

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

//IT is completed!!!!!! Let's go to the next exercise.