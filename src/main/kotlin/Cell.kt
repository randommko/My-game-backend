class Cell {
    private var type: cellTypes? = null

    enum class cellTypes {
        FREE,
        UNIT,
        ROCK
    }


    fun Cell(type: cellTypes?) {
        this.type = type
    }

    fun getType(): cellTypes? {
        return type
    }

    fun setType(type: cellTypes?) {
        this.type = type
    }
}