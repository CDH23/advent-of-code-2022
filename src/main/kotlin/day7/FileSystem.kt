package day7

sealed interface FileSystem

class Directory : FileSystem {
    val content = hashMapOf<String, FileSystem>()

    fun directory(name: String) = content.getOrPut(name) { Directory() }

    fun file(name: String, size: Long) = content.getOrPut(name) { File(size) }

    var size: Long = 0
}

class File(var size: Long) : FileSystem