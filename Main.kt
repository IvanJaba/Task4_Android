//Вариант № 10

import kotlin.math.abs

fun main() {
    val a = 5
    val b = -10

    val absA = abs(a)
    val absB = abs(b)

    println("Абсолютное значение $a: $absA")
    println("Абсолютное значение $b: $absB")

    val pieces = listOf(
        ChessPiece(PieceType.PAWN, PieceColor.WHITE, 2, 2),
        ChessPiece(PieceType.KNIGHT, PieceColor.WHITE, 1, 0),
        ChessPiece(PieceType.BISHOP, PieceColor.BLACK, 3, 5),
        ChessPiece(PieceType.ROOK, PieceColor.WHITE, 0, 0),
        ChessPiece(PieceType.QUEEN, PieceColor.BLACK, 4, 4),
        ChessPiece(PieceType.KING, PieceColor.WHITE, 4, 0)
    )

    for (piece in pieces) {
        println("\nФигура: ${piece.color} ${piece.type} на клетке (${piece.x}, ${piece.y})")
        println("Возможные ходы:")
        val possibleMoves = piece.validMoves()
        for ((x, y) in possibleMoves) {
            println("($x, $y)")
        }
        println("Интерфейс фигуры:")
        showPieceInterface(piece)
    }
}

enum class PieceType {
    PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
}

enum class PieceColor {
    WHITE, BLACK
}

data class ChessPiece(
    val type: PieceType, // Тип фигуры
    val color: PieceColor, // Цвет фигуры
    val x: Int, // Координата x на шахматной доске
    val y: Int // Координата y на шахматной доске
)

interface ChessPieceInterface {
    fun showInfo() // Метод для отображения информации о фигуре
}

fun ChessPiece.validMoves(): List<Pair<Int, Int>> {
    return when (type) {
        PieceType.PAWN -> {
            // Логика для ходов пешки
            listOf(Pair(x + 1, y), Pair(x - 1, y))
        }
        PieceType.KNIGHT -> {
            // Логика для ходов коня
            listOf(Pair(x + 2, y + 1), Pair(x - 2, y - 1))
        }
        PieceType.BISHOP -> {
            // Логика для ходов слона
            listOf(Pair(x + 1, y + 1), Pair(x - 1, y - 1))
        }
        PieceType.ROOK -> {
            // Логика для ходов ладьи
            listOf(Pair(x, y + 1), Pair(x, y - 1))
        }
        PieceType.QUEEN -> {
            // Логика для ходов королевы
            listOf(Pair(x + 1, y + 1), Pair(x - 1, y - 1))
        }
        PieceType.KING -> {
            // Логика для ходов короля
            listOf(Pair(x + 1, y), Pair(x - 1, y))
        }
    }
}

fun showPieceInterface(piece: ChessPiece) {
    when (piece.type) {
        PieceType.PAWN -> PawnInterface(piece.color, piece.x, piece.y).showInfo()
        PieceType.KNIGHT -> KnightInterface(piece.color, piece.x, piece.y).showInfo()
        PieceType.BISHOP -> BishopInterface(piece.color, piece.x, piece.y).showInfo()
        PieceType.ROOK -> RookInterface(piece.color, piece.x, piece.y).showInfo()
        PieceType.QUEEN -> QueenInterface(piece.color, piece.x, piece.y).showInfo()
        PieceType.KING -> KingInterface(piece.color, piece.x, piece.y).showInfo()
    }
}

class PawnInterface(private val color: PieceColor, private val x: Int, private val y: Int) : ChessPieceInterface {
    override fun showInfo() {
        println("Пешка $color на клетке ($x, $y)")
        println("Интерфейс: Описание пешки")
    }
}

class KnightInterface(private val color: PieceColor, private val x: Int, private val y: Int) : ChessPieceInterface {
    override fun showInfo() {
        println("Конь $color на клетке ($x, $y)")
        println("Интерфейс: Описание коня")
    }
}

class BishopInterface(private val color: PieceColor, private val x: Int, private val y: Int) : ChessPieceInterface {
    override fun showInfo() {
        println("Слон $color на клетке ($x, $y)")
        println("Интерфейс: Описание слона")
    }
}

class RookInterface(private val color: PieceColor, private val x: Int, private val y: Int) : ChessPieceInterface {
    override fun showInfo() {
        println("Ладья $color на клетке ($x, $y)")
        println("Интерфейс: Описание ладьи")
    }
}

class QueenInterface(private val color: PieceColor, private val x: Int, private val y: Int) : ChessPieceInterface {
    override fun showInfo() {
        println("Королева $color на клетке ($x, $y)")
        println("Интерфейс: Описание королевы")
    }
}

class KingInterface(private val color: PieceColor, private val x: Int, private val y: Int) : ChessPieceInterface {
    override fun showInfo() {
        println("Король $color на клетке ($x, $y)")
        println("Интерфейс: Описание короля")
    }
}
