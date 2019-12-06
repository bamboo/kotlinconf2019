package turtle.swing

import turtle.Degrees
import turtle.Distance
import turtle.Turtle
import java.awt.Color
import java.awt.Graphics
import java.awt.Image
import java.awt.Point
import java.awt.image.BufferedImage
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin


fun eval(width: Int = 400, height: Int = 400, program: Turtle.() -> Unit): Image =
    withTurtleGraphics(width, height, program)


fun withTurtleGraphics(width: Int, height: Int, program: (Turtle) -> Unit): Image =
    BufferedImage(width, height, BufferedImage.TYPE_INT_RGB).apply {
        createGraphics().run {
            background = Color.gray
            fillRect(0, 0, width, height)
            color = Color.black
            try {
                program(TurtleGraphics(this, Point(width / 2, height / 2)))
            } finally {
                dispose()
            }
        }
    }


class TurtleGraphics(
    private val graphics: Graphics,
    private val center: Point
) : Turtle {

    private
    val turtle = Turtle(Point(0, 0), 0.0)

    override var penColor: Color
        get() = graphics.color
        set(value) {
            graphics.color = value
        }

    override fun forward(distance: Distance) {
        turtle.forward(distance)?.let { (from, to) ->
            from.translate(center.x, center.y)
            to.translate(center.x, center.y)
            graphics.drawLine(from.x, from.y, to.x, to.y)
        }
    }

    override fun right(degrees: Degrees) {
        left(-degrees)
    }

    override fun left(degrees: Degrees) {
        turtle.left(degrees)
    }

    override fun penUp() {
        turtle.penUp()
    }

    override fun penDown() {
        turtle.penDown()
    }
}


private
class Turtle(
    private var position: Point,
    private var direction: Degrees
) {

    private
    var isDrawing = true

    fun forward(distance: Distance): Pair<Point, Point>? {
        val previousPosition = position
        val newPosition = pointAtDistance(distance)
        position = newPosition
        return if (isDrawing) previousPosition.location to newPosition.location else null
    }

    fun left(degrees: Degrees) {
        direction = (direction - degrees) % 360
    }

    private
    fun pointAtDistance(distance: Distance): Point =
        Math.toRadians(direction).let { radians ->
            position.run {
                Point(
                    (x + distance * cos(radians)).roundToInt(),
                    (y + distance * sin(radians)).roundToInt()
                )
            }
        }

    fun penUp() {
        isDrawing = false
    }

    fun penDown() {
        isDrawing = true
    }
}
