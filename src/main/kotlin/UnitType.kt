class UnitType {
    private var health: Int? = null
    private var damage: Int? = null
    private var speed: Int? = null
    private var visibleDistance: Int? = null
    private var regeneration: Int? = null
    private var myRace: Race? = null


    enum class Race {
        HUMAN,
        ORC
    }


    fun UnitType(race: Race) {
        myRace = race
        if (race == Race.HUMAN) {
            health = GameSettings.HUMAN_HEALTH
            damage = GameSettings.HUMAN_DAMAGE
            speed = GameSettings.HUMAN_SPEED
            visibleDistance = GameSettings.HUMAN_VISIBLE_DISTANCE
            regeneration = GameSettings.HUMAN_REGENERATION
        }
        if (race == Race.ORC) {
            health = GameSettings.ORC_HEALTH
            damage = GameSettings.ORC_DAMAGE
            speed = GameSettings.ORC_SPEED
            visibleDistance = GameSettings.ORC_VISIBLE_DISTANCE
            regeneration = GameSettings.ORC_REGENERATION
        }
    }

    fun getMyRace(): Race? {
        return myRace
    }

    fun getDamage(): Int {
        return damage!!
    }

    fun getHealth(): Int {
        return health!!
    }

    fun getRegeneration(): Int {
        return regeneration!!
    }

    fun getVisibleDistance(): Int {
        return visibleDistance!!
    }

    fun getSpeed(): Int {
        return speed!!
    }
}