/* 買酒
*/
import kotlin.math.roundToInt
class Wine {
    val TAVERN_NAME1 = "Hunter's Folly"
    var playerDragon = 20
    var playerGold = 0
    var playerSilver = 0
//    fun main() {
//        placeOrder("shandy,Dragon's Breath,5.91")
//    }

    fun performPurchase(price: Double) {
        displayBalance()
        val totalPurse = playerDragon * 1.34 + playerGold + (playerSilver / 100.0)
        println("錢包總金額： $totalPurse")
        println("購買項目金額： $price")
        val remainingBalance = totalPurse - price
        if (remainingBalance < 0) {
            println("Allen 的錢包不足，無法支出的金額：$price , 目前剩餘的金幣：$totalPurse")
            return
        }
        println("剩餘錢幣： ${"%.2f".format(remainingBalance)}")
        val remainingDragon = (remainingBalance / 1.34).toInt()
        val remainingGold = (remainingBalance % 1.34).toInt()
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        playerDragon = remainingDragon
        playerGold = remainingGold
        playerSilver = remainingSilver
        displayBalance()
        println("龍幣： ${"%.4f".format(remainingBalance / 1.43)}")
    }

    private fun displayBalance() {
        println("玩家錢包 各錢幣金額： 龍幣:$playerDragon  金幣: $playerGold , 銀幣: $playerSilver")
    }

    fun placeOrder(menuData: String) {
        val indexOfApstrophe = TAVERN_NAME1.indexOf('\'')
        val tavernMaster = TAVERN_NAME1.substring(0 until indexOfApstrophe)
        println("Allen 跟 $tavernMaster 說 關於點餐內容")  //wl speaks with Allen about their order.
        val (type, name, price) = menuData.split(',')
        var message = "Allen 買了一個 $name $type for $price." //"wl buys a $name ($type) for $price.
        println("Allen 買了一個 $name $type for $price")

        performPurchase(price.toDouble())
    }
}