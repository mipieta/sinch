package pl.mipieta

//The purpose of this class it to avoid mocking System.in and System.out
internal open class TestableIOProxy() {
    open fun readLine(): String? {
        return kotlin.io.readLine()
    }

    open fun println(line: Int) {
        kotlin.io.println(line)
    }
}
