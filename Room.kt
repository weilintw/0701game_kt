/*c14-20
*/
open class Room (val name:String){
    protected open val dangerLevel = 5
    fun description() = "Room:$name\n" + "Danger level : $dangerLevel"

    open fun load() = "這裡沒什麼可看的..."
    open fun ringBell() = "This is room bell"
}


open class TownSquare : Room("Town Square"){
    override val dangerLevel = super.dangerLevel -3
    private  var bellSound ="GWONG"
    final override fun load() = "The villagers rally and cheer as you enter！"
    final override fun ringBell() = "The bell tower announces your arrival. $bellSound"
}