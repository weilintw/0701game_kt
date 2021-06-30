import java.util.*

/*16-7
*/
interface Fightable{
    var healthPoints: Int
    val diceCount: Int
    val diceSides:Int
    val damageRoll: Int
        get() = (0 until diceCount).map{
            Random().nextInt(diceSides) + 1
        }.sum()

    fun attack(opponent: Fightable):Int
}
abstract class Monster(val name:String,
                       val description: String,
                       override var healthPoints: Int): Fightable{
//    override val diceCount: Int
//        get() = TODO("Not yet implemented")
//    override val diceSides: Int
//        get() = TODO("Not yet implemented")

    override fun attack(opponent: Fightable): Int {
        val damageDealt = damageRoll
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}
class Goblin (name: String = "妖精",
    description: String = "一隻討厭的地精",
    healthPoints: Int = 30): Monster(name, description, healthPoints){
        override val diceCount = 2
        override val diceSides = 8

}