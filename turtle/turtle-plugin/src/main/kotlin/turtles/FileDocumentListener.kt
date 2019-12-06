package turtles

import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManagerListener

class FileDocumentListener : FileDocumentManagerListener {

    override fun beforeDocumentSaving(document: Document) {
        println("beforeDocumentSaving(${document})")
    }
}