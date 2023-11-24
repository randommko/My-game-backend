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
            health = GameSettings.GlobalParams.HUMAN_HEALTH
            damage = GameSettings.GlobalParams.HUMAN_DAMAGE
            speed = GameSettings.GlobalParams.HUMAN_SPEED
            visibleDistance = GameSettings.GlobalParams.HUMAN_VISIBLE_DISTANCE
            regeneration = GameSettings.GlobalParams.HUMAN_REGENERATION
        }
        if (race == Race.ORC) {
            health = GameSettings.GlobalParams.ORC_HEALTH
            damage = GameSettings.GlobalParams.ORC_DAMAGE
            speed = GameSettings.GlobalParams.ORC_SPEED
            visibleDistance = GameSettings.GlobalParams.ORC_VISIBLE_DISTANCE
            regeneration = GameSettings.GlobalParams.ORC_REGENERATION
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