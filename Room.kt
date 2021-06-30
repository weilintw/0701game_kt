/*c16-8
*/
open class Room (val name:String){
    protected open val dangerLevel = 5
    var monster: Monster? = Goblin()
    fun description() = "空間： $name\r\n" + "危險等級 : $dangerLevel\r\n" + "怪物：${monster?.description ?: "沒有怪物"}"

    open fun load() = "這裡沒什麼可看的..."
    open fun ringBell() = "This is room bell"
}


open class TownSquare : Room("Town Square"){
    override val dangerLevel = super.dangerLevel -3
    private  var bellSound ="GWONG"
    final override fun load() = "The villagers rally and cheer as you enter！"
    final override fun ringBell() = "The bell tower announces your arrival. $bellSound"
}