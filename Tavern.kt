/* 酒館
*/
//import kotlin.math.roundToInt
import java.io.File
const val TAVERN_NAME = "Hunter's Folly"

class Menu {
    val menuList = File("data/tavern-menu-items.txt").readText().split("\n")
    var menuSet = mutableSetOf<String>()
    var nowType = ""
    fun getMenu() {
        println("******  歡迎來到 Hunter 的酒館  *******\r\n") //Welcome to Allen's Folly
        menuList.forEachIndexed { index, data ->
            placeOrder("顧客", data) //patron
        }
        menuList.all { data ->
            menuSet.add(data)
        }
    }

    private fun placeOrder(patronName: String, menuData: String) {
        val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
        val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
        // println("Madrigal speaks with $tavernMaster about their order.")
        val (type, name, price) = menuData.split(',')
        val priceStr = price.toString().trim()
        val dotlen = 34 - name.length - priceStr.length
        //println(name.length)
        //println(priceStr.length)
        var dotString = ""
        (0..dotlen).forEach {
            dotString += "."
        }
        //c10-12 進一步美化酒水單 begin
        val spacelen: Int = (30 - type.length) / 2
        var spaceString = ""
        (0..spacelen).forEach {
            spaceString += " "
        }
        if (type != nowType)
            println("$spaceString~ [$type] ~")
        //c10-12 進一步美化酒水單 end
        println("$name$dotString$price")
        nowType = type
    }
}