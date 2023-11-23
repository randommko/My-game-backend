class Coords {
    var X = 0
    var Y = 0

    fun Coords(x: Int, y: Int) {
        X = x
        Y = y
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null || javaClass != obj.javaClass) return false
        val coords = obj as Coords
        return (X == coords.X) and (Y == coords.Y)
    }

    override fun hashCode(): Int {
        return X * 10000 + Y
    }
}