package turtle

interface Turtle {
    fun forward(distance: Distance)
    fun left(degrees: Int) = left(degrees.toDouble())
    fun left(degrees: Degrees)
    fun right(degrees: Int) = right(degrees.toDouble())
    fun right(degrees: Degrees)
    var penColor: java.awt.Color
    fun penUp()
    fun penDown()
}

typealias Distance = Int

typealias Degrees = Double
