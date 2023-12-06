import java.util.concurrent.atomic.AtomicInteger

class Unit (unitType: UnitType, coords: Coords, id: Int) {
    private val count = AtomicInteger(0)
    private val id = id
    private var type: UnitType = unitType
    private var health = unitType.getHealth()
    private var isAlive = true

    init {
        println(
            (unitType.getMyRace().toString() + " №" + id +
                    " создан, координаты: " + coords.X).toString() + " ," + coords.Y
        )
    }

    fun getID(): Int {
        return id
    }

    fun getActualHelth(): Int {
        return health
    }

    fun getDamage(): Int {
        return type.getDamage()
    }

    fun getSpeed(): Int {
        return type.getSpeed()
    }

    fun getRegeneration(): Int {
        return type.getRegeneration()
    }

    fun getVisibleDistance(): Int {
        return type.getVisibleDistance()
    }

    fun setAlive(alive: Boolean) {
        isAlive = alive
    }

    fun isAlive(): Boolean {
        return isAlive
    }

    fun getUnitType(): UnitType {
        return type
    }

    fun takeDamage(damage: Int?): Int {
        health -= damage!!
        if (health < 0) {
            health = 0
            isAlive = false
            println(type.getMyRace().toString() + " №" + id +
                    "был убит")
        }
        return health
    }

}