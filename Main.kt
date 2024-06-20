interface DataIO {
    fun readData(): Any
    fun writeData(data: Any)
}

interface Operations {
    fun move(index: Int)
    fun performOperation()
}

abstract class NumberElement : DataIO, Operations {
    protected var data: Any? = null

    override fun readData(): Any {
        return data!!
    }

    override fun writeData(data: Any) {
        this.data = data
    }

    override fun move(index: Int) {
        // Реализация перемещения элемента в конец списка
    }

    override fun performOperation() {
        // Реализация выполнения арифметических операций над двумя последними элементами
    }
}

class RealNumber : NumberElement() {
    override fun readData(): Double {
        return super.readData() as Double
    }

    override fun writeData(data: Any) {
        super.writeData(data as Double)
    }
}

class ComplexNumberAlgebraic : NumberElement() {
    @Suppress("UNCHECKED_CAST")
    override fun readData(): Pair<Double, Double> {
        return super.readData() as Pair<Double, Double>
    }

    override fun writeData(data: Any) {
        super.writeData(data as Pair<*, *>)
    }
}

class ComplexNumberExponential : NumberElement() {
    @Suppress("UNCHECKED_CAST")
    override fun readData(): Triple<Double, Double, Double> {
        return super.readData() as Triple<Double, Double, Double>
    }

    override fun writeData(data: Any) {
        super.writeData(data as Triple<*, *, *>)
    }
}

class NumberList {
    private val list = mutableListOf<NumberElement>()

    fun addElement(element: NumberElement) {
        list.add(element)
    }

    fun moveElement(index: Int) {
        list[index].move(index)
    }

    fun performOperation() {
        list[list.size - 1].performOperation()
    }
}

fun main() {
    val numberList = NumberList()

    // Добавление элементов в список
    numberList.addElement(RealNumber())
    numberList.addElement(ComplexNumberAlgebraic())
    numberList.addElement(ComplexNumberExponential())

    // Перемещение элемента
    numberList.moveElement(1)

    // Выполнение операции
    numberList.performOperation()
}