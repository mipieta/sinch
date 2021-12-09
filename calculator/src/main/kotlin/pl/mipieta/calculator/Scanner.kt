package pl.mipieta.calculator

import java.util.*

internal class Scanner(private val allowedOperations: Set<String>) {
    fun indexOfNextOperatorToTheLeftFrom(tokens: LinkedList<String>, offset: Int): Int {

        val iterator = tokens.listIterator(offset)
        while (iterator.hasPrevious()) {
            val candidateIndex = iterator.previousIndex()
            val token = iterator.previous()
            if (allowedOperations.contains(token)) {
                return candidateIndex
            }
        }
        return -1
    }
}
