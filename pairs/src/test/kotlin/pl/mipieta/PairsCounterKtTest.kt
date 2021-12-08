package pl.mipieta

import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test


private val EOF = null

internal class PairsCounterKtTest {

    @Test
    fun simpleScenarioWithTwoPairs() {
        val io: TestableIOProxy = mock()

        whenever(io.readLine())
            .thenReturn("6")
            .thenReturn("2")
            .thenReturn("1")
            .thenReturn("4")
            .thenReturn("5")
            .thenReturn("3")
            .thenReturn(EOF)

        PairsCounter(io).printNumberOfPairsOnInput()

        verify(io, times(7)).readLine()
        verify(io).println(2)
    }

    @Test
    fun negativeNumbers() {
        val io: TestableIOProxy = mock()

        whenever(io.readLine())
            .thenReturn("0")
            .thenReturn("-1")
            .thenReturn("1")
            .thenReturn(EOF)

        PairsCounter(io).printNumberOfPairsOnInput()

        verify(io).println(1)
    }

    @Test
    fun ignoresEmptyLines() {
        val io: TestableIOProxy = mock()

        whenever(io.readLine())
            .thenReturn("")
            .thenReturn("")
            .thenReturn("4")
            .thenReturn("")
            .thenReturn("")
            .thenReturn("")
            .thenReturn("0")
            .thenReturn("1")
            .thenReturn("")
            .thenReturn("2")
            .thenReturn("3")
            .thenReturn("")
            .thenReturn("")
            .thenReturn("")
            .thenReturn("4")
            .thenReturn("")
            .thenReturn("5")
            .thenReturn("")
            .thenReturn(EOF)

        PairsCounter(io).printNumberOfPairsOnInput()

        verify(io, times(19)).readLine()
        verify(io).println(2)
    }

    @Test
    fun shortestInput() {
        val io: TestableIOProxy = mock()

        whenever(io.readLine())
            .thenReturn("3")
            .thenReturn("1")
            .thenReturn("2")
            .thenReturn(EOF)

        PairsCounter(io).printNumberOfPairsOnInput()

        verify(io).println(1)
    }

    @Test
    fun bigValues() {
        val io: TestableIOProxy = mock()

        whenever(io.readLine())
            .thenReturn(Integer.MIN_VALUE.toString())
            .thenReturn(Integer.MAX_VALUE.toString())
            .thenReturn(Integer.MIN_VALUE.toString())
            .thenReturn(EOF)

        PairsCounter(io).printNumberOfPairsOnInput()

        verify(io).println(0)
    }

    @Test
    fun intUnderflow() {
        val io: TestableIOProxy = mock()

        whenever(io.readLine())
            .thenReturn("-1")
            .thenReturn(Integer.MAX_VALUE.toString())
            .thenReturn(Integer.MIN_VALUE.toString())
            .thenReturn(EOF)

        PairsCounter(io).printNumberOfPairsOnInput()

        verify(io).println(1)
    }


}
