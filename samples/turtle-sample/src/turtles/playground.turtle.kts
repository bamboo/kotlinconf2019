
val colors = listOf(
    Color.blue,
    Color.red,
    Color.yellow
)

fun magic() {
//    penColor = colors.random()
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
}

penUp()
left(180)
forward(172)
right(180)
penDown()

repeat(120) {
    magic()
    right(3)
}