import java.io.File
/*c15-20
 */
class Player(_name: String, var healthPoints: Int = 100, val isBlessed: Boolean, private val isImmortal: Boolean){
        var name = _name
        get() = "${field.capitalize()} of $hometown    "
        private set(value) {
            field = value.trim()
        }
    val hometown by lazy {selectHometown()}
    var currentPosition = Coordinate(0,0)

    init {
        require(healthPoints > 0,{"健康點數需大於0"})
        require(name.isNotBlank(),{"玩家一定要有名字"})
    }

    constructor(name: String):this(name,isBlessed = true,isImmortal = false){
        if (name.toLowerCase() == "kar") healthPoints = 40
    }


    fun auraColor() =
        if (isBlessed && healthPoints > 50 || isImmortal) "綠色" else "無光環"

    fun formaHealthStatus() =
        when (healthPoints) {
            100 -> "健康狀態極佳"
            in 90..99 -> "有一些小擦傷"
            in 75..89 -> if (isBlessed) {
                "雖有一些傷口，但恢復很快"
            } else {
                "有一些傷口"
            }
            in 15..74 -> "嚴重受傷"
            //顯示玩家狀態
            else -> "情況不妙"
        }

    fun castFireBall(numFireballs: Int = 2): Int {
        println("FireBall杯數：$numFireballs")
        return numFireballs
    }
    private fun selectHometown() = File("data/towns.txt")
        .readText().split("\r\n").shuffled().first()
}