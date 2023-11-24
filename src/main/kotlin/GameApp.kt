import java.util.*
import kotlin.collections.HashMap

class GameApp {
    private val gameField = HashMap<Coords, Cell>()
    private lateinit var humanUnitType: UnitType
    private lateinit var orcUnitType: UnitType
    private var humanUnitActualNumber: Int = 1
    private var orcUnitActualNumber: Int = 1


    val humanUnits = HashMap<Coords, Unit>()
    val orcUnits = HashMap<Coords, Unit>()
    val gameUnitGroups = HashMap<UnitType.Race, HashMap<Coords, Unit>>()

    fun initUnitType() {
        humanUnitType = UnitType(UnitType.Race.HUMAN)
        orcUnitType = UnitType(UnitType.Race.ORC)

        gameUnitGroups[UnitType.Race.HUMAN] = humanUnits
        gameUnitGroups[UnitType.Race.ORC] = orcUnits
    }

    fun initUnits() {
        for (group in gameUnitGroups) {
            var unitQty = 0

            if (group.key == UnitType.Race.HUMAN)
                unitQty = GameSettings.GlobalParams.HUMAN_MAX_QTY

            else if (group.key == UnitType.Race.ORC)
                unitQty = GameSettings.GlobalParams.ORC_MAX_QTY

            for (i in 0..< unitQty) {
                var coords: Coords
                do {
                    //TODO: возможен бесконечный цикл если все поле занято
                    coords = Coords(
                        Random().nextInt(GameSettings.GlobalParams.FIELD_X - 1),
                        Random().nextInt(GameSettings.GlobalParams.FIELD_Y - 1)
                    )
                } while (gameField.containsKey(coords))

                gameField[coords] = Cell(Cell.CellTypes.UNIT)

                if (group.key == UnitType.Race.HUMAN)
                    group.value[coords] = Unit(humanUnitType, coords, humanUnitActualNumber++)

                if (group.key == UnitType.Race.ORC)
                    group.value[coords] = Unit(orcUnitType, coords, orcUnitActualNumber++)
            }
        }
    }

    fun printField() {
        print("   ")
        for (i in 0 until GameSettings.GlobalParams.FIELD_X) {
            print("$i  ")
        }
        println()
        for (i in 0 until GameSettings.GlobalParams.FIELD_X) {
            print("$i  ")
            for (j in 0 until GameSettings.GlobalParams.FIELD_Y) {
                if (!gameField.containsKey(Coords(i, j))) print("*  ")
                //TODO: переписать вывод поля с учетом чот существуют объекты только у юнитов скал
                if (gameField.containsKey(Coords(i, j))) {
                    if (gameField[Coords(i, j)]!!.getType() == Cell.CellTypes.ROCK) print("R  ")
                    if (gameField[Coords(i, j)]!!.getType() == Cell.CellTypes.UNIT) {
                        if (humanUnits.containsKey(Coords(i, j))) print("H  ")
                        if (orcUnits.containsKey(Coords(i, j))) print("O  ")
                    }
                }
            }
            println()
        }
        println("--- --- --- ---")
    }

    fun doGameStep(group: UnitType.Race, unitMap: HashMap<Coords, Unit>) {

        //Передвинуть юнитов
        //Выбрать в какую сторону двигаться - getMoveDestination(Coords actualCoords)
        //Получить координаты передвижения - getMoveCroords(Coords unitCoords, int delta_x, int delta_y)
        //Проверить занятость клетки - if (...)
        //При необходимости атаковать если юнит в клетке ЖИВ
        //Если победа, то передвинуть юнита

        val movedUnits = HashMap<Coords, Coords>()  //key = новые координаты, value = старые координаты

        for ((actualCoords, unit) in unitMap) {
            val destination: Coords = getMoveDestination(actualCoords)
            val newCoords: Coords = getMoveCoords(
                actualCoords,
                destination.X * unit.getSpeed(),
                destination.Y * unit.getSpeed()
            )

            if (gameField.containsKey(newCoords))
                //TODO: драка
            else if (!gameField.containsKey(newCoords))
                movedUnits[newCoords] = actualCoords
        }

        for ((key, value) in movedUnits) {
            if (key != value)
                moveUnit(unitMap, key, value)   //key = новые координаты, value = старые координаты
        }
    }

    private fun getMoveDestination(actualCoords: Coords): Coords {
        val destination = Coords(0, 0)

        val rnd = Random()
        var temp = rnd.nextInt(100) //число для координаты Х

        if (temp > 66)
            destination.X = 1
        if ((temp >= 33) and (temp <= 66))
            destination.X = 0
        if (temp < 39)
            destination.X = -1

        temp = rnd.nextInt(100) //число для координаты У
        if (temp > 66)
            destination.Y = 1
        if ((temp >= 33) and (temp <= 66))
            destination.Y = 0
        if (temp < 33)
            destination.Y = -1

        //прверка на границы поля
        if (actualCoords.X == 0) destination.X = 1
        if (actualCoords.X == GameSettings.GlobalParams.FIELD_X - 1) destination.X = -1
        if (actualCoords.Y == 0) destination.Y = 1
        if (actualCoords.Y == GameSettings.GlobalParams.FIELD_X - 1) destination.Y = -1
        return destination
    }

    private fun getMoveCoords(unitCoords: Coords, deltaX: Int, deltaY: Int): Coords {
        val newCoords = Coords(0, 0)
        if (unitCoords.X + deltaX > GameSettings.GlobalParams.FIELD_X - 1)
            newCoords.X = GameSettings.GlobalParams.FIELD_X - 1
        else if (unitCoords.X + deltaX < 0)
            newCoords.X = 0 else newCoords.X = unitCoords.X + deltaX

        if (unitCoords.Y + deltaY > GameSettings.GlobalParams.FIELD_Y - 1)
            newCoords.Y = GameSettings.GlobalParams.FIELD_Y - 1
        else if (unitCoords.Y + deltaY < 0)
            newCoords.Y = 0
        else newCoords.Y = unitCoords.Y + deltaY

        return newCoords
    }

    private fun moveUnit(unitMap: HashMap<Coords, Unit>, newCoords: Coords, oldCoors: Coords) {
        //TODO: нельзя удалять объект из мапы пока ты итерируешься по мапе
        println(
            unitMap[oldCoors]?.getUnitType()?.getMyRace()
                .toString() + "#" + unitMap[oldCoors]?.getID() + " переместился: " +
                    oldCoors.X + " ," + oldCoors.Y + " -> " + newCoords.X + " ," + newCoords.Y
        )
        unitMap[newCoords] = unitMap[oldCoors]!!
        unitMap.remove(oldCoors)
        gameField.remove(oldCoors)
        gameField[newCoords] = Cell(Cell.CellTypes.UNIT)
    }

}