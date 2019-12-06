package turtle.scripting

import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import turtle.Turtle
import turtle.scripting.host.TurtleScriptHost
import java.awt.Color
import kotlin.script.experimental.api.valueOrThrow


class TurtleScriptTest {

    @Test
    fun `it works`() {

        val turtle = mock<Turtle>()
        val host = TurtleScriptHost()
        val result = host.eval(
            """
                penColor = Color.green
                forward(10)
            """.trimIndent(),
            turtle
        )
        result.valueOrThrow()

        inOrder(turtle) {
            verify(turtle).penColor = Color.green
            verify(turtle).forward(10)
            verifyNoMoreInteractions()
        }
    }
}
