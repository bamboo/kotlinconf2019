package groddler.scripting

import com.nhaarman.mockitokotlin2.*
import groddler.api.Action
import groddler.api.Project
import groddler.api.Task
import groddler.scripting.host.ProjectScriptHost
import org.junit.Test
import kotlin.script.experimental.api.valueOrThrow

class ProjectScriptHostTest {

    @Test
    fun `it works`() {

        val task = mock<Task>()
        val project = mock<Project> {
            on { task(any(), any()) } doAnswer { invocation ->
                // force task configuration block to run
                invocation.getArgument<Action<Task>>(1).execute(task)
            }
        }

        val host = ProjectScriptHost()
        val result = host.eval(
            """
                plugins { id("kotlin") } 
                kotlin { isAwesome = true }
                task("make an apple pie") {
                    dependsOn("invent the universe")
                    perform { println("🥧") }
                }
            """,
            project
        )
        result.valueOrThrow()

        inOrder(project, task) {
            verify(project).task(eq("make an apple pie"), any())
            verify(task).dependsOn(eq("invent the universe"))
            verify(task).perform(any())
            verifyNoMoreInteractions()
        }
    }
}


