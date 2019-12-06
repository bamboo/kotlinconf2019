package turtles

import com.intellij.AppTopics
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManagerListener
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerEvent
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.impl.PsiManagerEx
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.layout.panel
import com.intellij.util.messages.MessageBusConnection
import turtle.Turtle
import turtle.scripting.host.TurtleScriptHost
import turtle.swing.withTurtleGraphics
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.util.concurrent.atomic.AtomicReference
import javax.swing.ImageIcon
import javax.swing.JLabel


class TurtlePreviewToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        TurtlePreview(project).run {

            val contentFactory = ContentFactory.SERVICE.getInstance()
            val content = contentFactory.createContent(ui, "", false)

            toolWindow.contentManager.addContent(content)

            invokeLater {
                project.messageBus.connect(content).apply {
                    attach()
                }
            }
        }
    }
}


private
class TurtlePreview(val project: Project) {

    private
    val turtleHost = TurtleScriptHost()

    private
    val size = AtomicReference<Dimension>(Dimension(400, 400))

    private
    var selectedFile: VirtualFile? = selectedFile()

    private
    val activeEditorLabel = JLabel(computeLabel())

    private
    val canvas = JLabel("")

    val ui = panel {
        row {
            activeEditorLabel()
        }
        row {
            scrollPane(canvas).component.addComponentListener(
                object : ComponentAdapter() {
                    override fun componentResized(e: ComponentEvent) {
                        size.set(e.component.size)
                    }
                }
            )
        }
    }

    fun MessageBusConnection.attach() {
        subscribe(
            FileEditorManagerListener.FILE_EDITOR_MANAGER,
            object : FileEditorManagerListener {
                override fun selectionChanged(event: FileEditorManagerEvent) {
                    selectedFile = event.newEditor?.file
                        ?.takeIf { it.name.endsWith(".turtle.kts") }
                    selectedFileChanged()
                }
            }
        )
        subscribe(
            AppTopics.FILE_DOCUMENT_SYNC,
            object : FileDocumentManagerListener {
                override fun beforeDocumentSaving(document: Document) {
                    psiDocumentManager().getPsiFile(document)?.let { psiFile ->
                        if (psiFile.virtualFile == selectedFile) {
                            selectedFileContentsChanged(document.text)
                        }
                    }
                }
            }
        )
    }

    private
    fun selectedFileContentsChanged(text: String) {

        val dimension = size.get()

        ProgressManager.getInstance().executeNonCancelableSection {

            val newImage =
                withContextClassLoader(Turtle::class.java.classLoader) {
                    withTurtleGraphics(dimension.width, dimension.height) { turtleDsl ->
                        val result = turtleHost.eval(text, turtleDsl)
                        println(result)
                    }
                }

            invokeLater {
                canvas.icon = ImageIcon(newImage)
            }
        }
    }

    private
    fun selectedFileChanged() {
        val message = computeLabel()
        val selectedFileText = selectedFile?.let {
            PsiManagerEx.getInstanceEx(project).findFile(it)?.let { selectedPsiFile ->
                psiDocumentManager().getDocument(selectedPsiFile)?.text
            }
        }

        invokeLater {
            activeEditorLabel.text = message
        }
        selectedFileContentsChanged(selectedFileText ?: "")
    }

    private
    fun computeLabel() = selectedFile?.name ?: "no file"

    private
    fun selectedFile() = FileEditorManager.getInstance(project).selectedEditor?.file

    private
    fun psiDocumentManager() = PsiDocumentManager.getInstance(project)
}


private
inline fun <T> withContextClassLoader(classLoader: ClassLoader, block: () -> T): T {
    val previous = Thread.currentThread().contextClassLoader
    Thread.currentThread().contextClassLoader = classLoader
    try {
        return block()
    } finally {
        Thread.currentThread().contextClassLoader = previous
    }
}


private
fun invokeLater(block: () -> Unit) =
    ApplicationManager.getApplication().invokeLater(block)
