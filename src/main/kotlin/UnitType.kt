class UnitType (race: Race) {

    private var health: Int? = null
    private var damage: Int? = null
    private var speed: Int? = null
    private var visibleDistance: Int? = null
    private var regeneration: Int? = null
    private var myRace: Race = race

    enum class Race {
        HUMAN,
        ORC
    }
    init {
        if (race == Race.HUMAN) {
            health = GameSettings.globalParams.HUMAN_DAMAGE
            damage = GameSettings.globalParams.HUMAN_DAMAGE
            speed = GameSettings.globalParams.HUMAN_SPEED
            visibleDistance = GameSettings.globalParams.HUMAN_VISIBLE_DISTANCE
            regeneration = GameSettings.globalParams.HUMAN_REGENERATION
        }
        if (race == Race.ORC) {
            health = GameSettings.globalParams.ORC_HEALTH
            damage = GameSettings.globalParams.ORC_DAMAGE
            speed = GameSettings.globalParams.ORC_SPEED
            visibleDistance = GameSettings.globalParams.ORC_VISIBLE_DISTANCE
            regeneration = GameSettings.globalParams.ORC_REGENERATION
        }
    }

    fun getMyRace(): Race {
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