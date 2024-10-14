fun main() {
    // Set the highest offer as the winning price
    val winningBid = Bid(5000, "Private Collector")

    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
    println("Item B is sold at ${auctionPrice(null, 3000)}.")
}

// This is the Bid class
class Bid(val amount: Int, val bidder: String)

// This function will return the amount of the bid IF there is a winner, otherwise will return the minimum price
//fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
    //if(bid != null){
        //return bid.amount
    //}else{
        //return minimumPrice
    //}
//} 

// Refactorying the actionPrice function
fun auctionPrice(bid: Bid?, minimumPrice: Int) = bid?.amount ?: minimumPrice
