import java.util.concurrent.atomic.AtomicInteger

class Unit {
    private val count = AtomicInteger(0)
    private val id = 0
    private val type: UnitType
    private var health = 0
    private var isAlive = false

    fun Unit(unitType: UnitType, coords: Coords) {
        id = Unit.count.incrementAndGet()
        type = unitType
        health = unitType.getHealth()
        isAlive = true
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

    fun getUnitType(): UnitType? {
        return type
    }

    fun takeDamage(damage: Int): Int {
        health = health - damage
        if (health < 0) {
            health = 0
            isAlive = false
        }
        return health
    }

    fun attack(enemy: Unit) {
        //наносим урон врагу
        //если враг выжил - враг атакует нас
        if (enemy.takeDamage(getDamage()) > 0) takeDamage(enemy.getDamage())
    }
}