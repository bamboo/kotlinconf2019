package turtle.scripting.host

import turtle.Turtle
import turtle.scripting.TurtleScript
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.SourceCode
import kotlin.script.experimental.api.constructorArgs
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

class TurtleScriptHost {

    private
    val scriptingHost = BasicJvmScriptingHost()

    /**
     * Evaluates the given Turtle script [sourceCode] against the given [turtle].
     */
    fun eval(
        sourceCode: SourceCode,
        turtle: Turtle
    ): ResultWithDiagnostics<EvaluationResult> =
        scriptingHost.evalWithTemplate<TurtleScript>(
            sourceCode,
            evaluation = {
                constructorArgs(turtle)
            }
        )

    /**
     * Evaluates the given Turtle script [sourceCode] against the
     * given [turtle].
     */
    fun eval(
        sourceCode: String,
        turtle: Turtle
    ) = eval(sourceCode.toScriptSource(), turtle)
}