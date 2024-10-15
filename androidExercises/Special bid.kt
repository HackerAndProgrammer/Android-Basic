package com.example.androidmaster.androidExercises

/**
 * Por lo general, en una subasta,
 * el ofertante que ofrece el importe más alto determina el precio de un artículo. En esta subasta especial, si nadie oferta por un artículo,
 * este se vende automáticamente a la casa de subastas al precio mínimo.
 *
 * En el código inicial que se proporciona en el siguiente fragmento de código,
 * se te proporciona una función auctionPrice() que acepta un tipo Bid? anulable como argumento:
 */

/**
 * RESULT:
 * Item A is sold at 5000.
 * Item B is sold at 3000.
 */

fun main() {
    // Set the highest offer as the winning price
    val winningBid = Bid(5000, "Private Collector")

    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
    println("Item B is sold at ${auctionPrice(null, 3000)}.")
}

// This is the Bid class
class Bid(val amount: Int, val bidder: String)

//This function will return the amount of the bid IF there is a winner, otherwise will return the minimum price

//fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
//    if (bid != null) {
//        return bid.amount
//    } else {
//        return minimumPrice
//    }
//}

// Refactorying the actionPrice function

fun auctionPrice(bid: Bid?, minimumPrice: Int) = bid?.amount ?: minimumPrice

//IT is completed!!!!!! Our journey in basic Android exercises is done!!.