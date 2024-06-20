//Вариант № 10

import kotlin.math.abs

// Класс, представляющий шахматную фигуру
fun main() {
    val a = 5
    val b = -10

    val absA = abs(a)
    val absB = abs(b)

    println("Абсолютное значение $a: $absA")
    println("Абсолютное значение $b: $absB")
}


data class ChessPiece(
    val type: PieceType, // Тип фигуры
    val color: PieceColor, // Цвет фигуры
    val x: Int, // Координата x на шахматной доске
    val y: Int // Координата y на шахматной доске
)

// Перечисление, описывающее типы шахматных фигур
enum class PieceType {
    PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
}

// Перечисление, описывающее цвета шахматных фигур
enum class PieceColor {
    WHITE, BLACK
}

// Функция, сортирующая список шахматных фигур по типу, а затем по цвету
fun sortPiecesByRank(pieces: List<ChessPiece>): List<ChessPiece> {
    return pieces.sortedWith(
        compareBy(
            { it.type },
            { it.color }
        )
    )
}

// Функция, возвращающая список шахматных фигур заданного цвета
fun findPiecesByColor(pieces: List<ChessPiece>, color: PieceColor): List<ChessPiece> {
    return pieces.filter { it.color == color }
}

// Функция, возвращающая список шахматных фигур, угрожающих заданной клетке
fun findPiecesThreatening(pieces: List<ChessPiece>, x: Int, y: Int): List<ChessPiece> {
    return pieces.filter { isThreateningSquare(it, x, y) }
}

// Функция, возвращающая список шахматных фигур, не угрожающих заданной клетке
fun findPiecesNotThreatening(pieces: List<ChessPiece>, x: Int, y: Int): List<ChessPiece> {
    return pieces.filter { !isThreateningSquare(it, x, y) }
}

// Вспомогательная функция, определяющая, угрожает ли фигура заданной клетке
private fun isThreateningSquare(piece: ChessPiece, x: Int, y: Int): Boolean {
    when (piece.type) {
        PieceType.PAWN -> {
            val isWhite = piece.color == PieceColor.WHITE
            val forwardDirection = if (isWhite) 1 else -1
            return (piece.x == x && piece.y + forwardDirection == y) ||
                    (abs(piece.x - x) == 1 && piece.y + forwardDirection == y)
        }
        PieceType.KNIGHT -> {
            val dxAbs = abs(piece.x - x)
            val dyAbs = abs(piece.y - y)
            return (dxAbs == 2 && dyAbs == 1) || (dxAbs == 1 && dyAbs == 2)
        }
        PieceType.BISHOP -> {
            val dx = x - piece.x
            val dy = y - piece.y
            return abs(dx) == abs(dy)
        }
        PieceType.ROOK -> {
            return piece.x == x || piece.y == y
        }
        PieceType.QUEEN -> {
            val dx = x - piece.x
            val dy = y - piece.y
            return abs(dx) == abs(dy) || piece.x == x || piece.y == y
        }
        PieceType.KING -> {
            val dx = abs(x - piece.x)
            val dy = abs(y - piece.y)
            return (dx == 1 && dy <= 1) || (dx <= 1 && dy == 1)
        }
    }
}
