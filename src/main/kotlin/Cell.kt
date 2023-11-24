class Cell (
    private var type: CellTypes) {

    enum class CellTypes {
        FREE,
        UNIT,
        ROCK
    }

    fun getType(): CellTypes {
        return type
    }

    fun setType(type: CellTypes) {
        this.type = type
    }
}