package turtle.swing

import turtle.Turtle
import java.awt.Canvas
import java.awt.Color
import java.awt.EventQueue
import java.awt.Graphics
import java.awt.Image
import javax.swing.JFrame


fun main() {
    EventQueue.invokeLater {
        val width = 800
        val height = 600
        val image = eval(width, height, Turtle::myTurtleProgram)
        TurtleFrame(width, height, image).run {
            isVisible = true
        }
    }
}


private
fun Turtle.myTurtleProgram() {
    magicShape()
    repeat(4) {
        left(90)
        forward(42)
    }
}


private
fun Turtle.magicShape() {
    left(180)
    forward(72)
    right(180)
    repeat(144) {
        penColor = listOf(Color.blue, Color.red, Color.yellow).random()
        left(90)
        penUp()
        forward(60)
        penDown()
        repeat(4) {
            right(72)
            forward(60)
        }
        left(36)
        forward(60)
        repeat(3) {
            right(72)
            forward(60)
        }
        right(10)
    }
}


class TurtleFrame(
    width: Int,
    height: Int,
    image: Image
) : JFrame() {

    init {
        title = "Turtle Graphics"
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(width, height)
        setLocationRelativeTo(null)

        add(object : Canvas() {
            override fun paint(g: Graphics) {
                println("paint")
                g.drawImage(
                    image,
                    0,
                    0,
                    image.getWidth(this),
                    image.getHeight(this),
                    Color.gray,
                    this
                )
            }
        })
    }
}

