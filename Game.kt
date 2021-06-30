import java.lang.IllegalStateException
import kotlin.system.exitProcess
/*c16-12
*/
fun main() {
    Game.play()
}
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
        println("歡迎你，冒險家！")
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
            "fight" -> fight()
            "menu" -> currentRoom.menu.getMenu()
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
        return
    }
    private fun checkQuit(){
        isQuit=true;
        println("感謝你，再見！")
    }
    private fun move(directionInput: String) =
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
    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0){
            slay(it)
            Thread.sleep(1000)
        }
        "戰鬥確認"
    } ?: "偵測無戰鬥"
    private fun slay(monster: Monster){
        println("${monster.name} 攻擊 ${monster.attack((player))}點 傷害！")
        println("${player.name} 攻擊 ${player.attack((monster))}點 傷害！")

        if (player.healthPoints <= 0){
            println(">>>> 你已被擊敗！感謝你的遊玩 <<<<")
            exitProcess(0)
        }
        if (monster.healthPoints <= 0){
            println(">>>> ${monster.name} 已被擊敗")
            currentRoom.monster = null
        }
    }
}