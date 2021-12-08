package pl.mipieta

internal class PairsCounter(private val io: TestableIOProxy) {

    fun printNumberOfPairsOnInput() {
        val unmatchedNumbers = mutableSetOf<Int>() //expected max allocation: 10e6 x 4 bytes < 4 MB
        var matchesCounter = 0

        val expectedSum = readExpectedSum()

        while (true) {
            val line = io.readLine() ?: break
            if (line.isEmpty()) continue

            val uniqueNumber = line.toInt()
            val matchingNumber =
                expectedSum - uniqueNumber //underflow may occur, but it's safe as long as matchingNumber is not stored anywhere

            if (unmatchedNumbers.contains(matchingNumber)) {
                ++matchesCounter
                unmatchedNumbers.remove(matchingNumber) //it will not match again because the input numbers are unique
            } else {
                unmatchedNumbers.add(uniqueNumber)
            }
        }

        io.println(matchesCounter)
    }

    private fun readExpectedSum(): Int {
        while (true) {
            val line = io.readLine()
            if (line!!.isBlank()) continue
            return line.toInt()
        }
    }
}

fun main(args: Array<String>) {
    PairsCounter(TestableIOProxy()).printNumberOfPairsOnInput()
}
