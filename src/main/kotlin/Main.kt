import java.util.*

fun main() {
    var gameApp = GameApp()
    val scanner = Scanner(System.`in`)
    gameApp.InitUnitType()
    gameApp.initUnits()
    var check = true

    Thread {
        while (check) {
            gameApp.printField()
            gameApp.doGameStep(gameApp.humanUnits)
            gameApp.doGameStep(gameApp.orcUnits)
            try {
                Thread.sleep(300)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }.start()

    Thread {
        if (scanner.hasNext()) {
            check = false
        }
    }.start()
}
