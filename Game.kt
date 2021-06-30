import java.lang.IllegalStateException

/*c15-20*/
/*挑戰練習
15.9: 實作 quit命令(Game.kt)
15.10 挑戰練習:魔力地圖
5.11  挑戰練習:搖鈴
*/
fun main() {
//呼叫 Player 類別的主建構函數，產生一個類別實體
//    var player = Player("wl")
//    player.castFireBall(5)
//
//    var currentRoom = TownSquare() //Room("Foyer")
//    println(currentRoom.description())
//    println(currentRoom.load())
//
//    player.auraColor()
//    printPlayerStatus(player)
    Game.play()
}

//private fun printPlayerStatus(player:Player) {
//    println(
//        "光環顏色：${player.auraColor()}" + "       走運嗎？" +
//                "${if (player.isBlessed) "是的" else "否"}"
//    )
//    println("${player.name}${player.formaHealthStatus()}")
//}
object  Game{
    final var isQuit =false
    private var player = Player("wl")
    var currentRoom: Room = TownSquare()
    private  var worldMap = listOf(
        listOf(currentRoom,Room("Tavern"),Room("Back Room")),
        listOf(Room("Long Corridor"),Room("Generic Room")),

    )
    fun play() {
        while (!isQuit){
            println(currentRoom.description())
            println(currentRoom.load())
            player.auraColor()
            printPlayerStatus(player)
            print("> Enter you command: ")
            //println("Last command: ${readLine()}")
            println(GameInput(readLine()).processCommand())
        }
    }

    init {
        println("Welcome, adventurer")
        player.castFireBall(5)
    }
    private fun printPlayerStatus(player:Player) {
        println(
            "光環顏色：${player.auraColor()}" + "       走運嗎？" +
                    "${if (player.isBlessed) "是的" else "否"}"
        )
        println("${player.name}${player.formaHealthStatus()}")
    }
    private  class GameInput(arg: String?){
        private  val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1,{""})
        fun processCommand() = when (command.toLowerCase()){
            "quit" -> checkQuit()
            "exit" -> checkQuit()
            "map" -> showMap()
            "ring" -> currentRoom.ringBell()
            "move" -> move(argument)
            else -> commandNotFound()
        }
        private  fun commandNotFound() ="I'm not quite sure what you're trying to do!"
    }


    private fun showMap(){
        val  x= player.currentPosition.x
        val  y =player.currentPosition.y
        //println("x=$x,y=$y")
        if(y==0){
            if(x==0) println("XOO")
            if(x==1) println("OXO")
            if(x==2) println("OOX")
            println("OO")
        }
        else{
            println("OOO")
            if(x==0) println("XO")
            if(x==1) println("OX")
        }


    }

    private  fun checkQuit(){
        isQuit=true;
        println("感謝你，再見！")
    }
    private  fun move(directionInput: String) =
        try {
            val direction =Direction.valueOf(directionInput.toUpperCase())
            val newPosition =direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds){
                throw IllegalStateException("$direction is out of bounds.")
            }
            val newRoom  = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        } catch (e: Exception){
            "Invalid direction: $directionInput. "
        }
}