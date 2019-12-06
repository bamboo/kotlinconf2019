package turtles

import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener

class EditorListener : DocumentListener {
    override fun documentChanged(event: DocumentEvent) {
        println(event)
    }
}