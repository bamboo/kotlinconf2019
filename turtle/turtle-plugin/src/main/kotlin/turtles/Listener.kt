package turtles

import com.intellij.openapi.vfs.VirtualFileManagerListener

class Listener : VirtualFileManagerListener {
    override fun afterRefreshFinish(asynchronous: Boolean) {}
}