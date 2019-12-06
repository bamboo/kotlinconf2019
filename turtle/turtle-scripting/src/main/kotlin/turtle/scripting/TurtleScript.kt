package turtle.scripting

import turtle.Turtle
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm

@KotlinScript(
    displayName = "Turtle script",
    fileExtension = "turtle.kts",
    compilationConfiguration = TurtleScriptCompilationConfiguration::class
)
abstract class TurtleScript(turtle: Turtle) : Turtle by turtle

internal
object TurtleScriptCompilationConfiguration : ScriptCompilationConfiguration({

    jvm {
        dependenciesFromClassContext(
            TurtleScript::class,
            "turtle-scripting",
            "turtle-graphics",
            "kotlin-stdlib"
        )
    }

    ide {
        acceptedLocations(ScriptAcceptedLocation.Everywhere)
    }

    defaultImports(
        java.awt.Color::class
    )
})