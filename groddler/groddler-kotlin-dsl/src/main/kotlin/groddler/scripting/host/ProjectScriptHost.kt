package groddler.scripting.host

import groddler.api.Project
import groddler.scripting.ProjectScript
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.SourceCode
import kotlin.script.experimental.api.constructorArgs
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost


class ProjectScriptHost {

    private
    val scriptingHost = BasicJvmScriptingHost()

    /**
     * Evaluates the given project script [sourceCode] against the
     * given [project].
     */
    fun eval(
        sourceCode: String,
        project: Project
    ) = eval(sourceCode.toScriptSource(), project)

    /**
     * Evaluates the given project script [sourceCode] against the
     * given [project].
     */
    fun eval(
        sourceCode: SourceCode,
        project: Project
    ): ResultWithDiagnostics<EvaluationResult> =
        scriptingHost.evalWithTemplate<ProjectScript>(
            sourceCode,
            evaluation = {
                constructorArgs(project)
            }
        )
}
