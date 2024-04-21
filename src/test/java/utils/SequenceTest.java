package utils;

import org.example.utils.Sequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequenceTest {

    @Test
    void getNextReturnsNextIntValueStartingWithOne() {
        final var sequence = new Sequence();

        final var firstValue = sequence.getNext();
        final var secondValue = sequence.getNext();
        final var thirdValue = sequence.getNext();

        assertEquals(1, firstValue);
        assertEquals(2, secondValue);
        assertEquals(3, thirdValue);
    }

    @Test
    void getNextOnOverloadingReturnsZero() {
        final var sequence = new Sequence();

        for (int i = 1; i < Integer.MAX_VALUE; ++i) {
            sequence.getNext();
        }
        final var maxValue = sequence.getNext();
        final var valueAfterMaxValue = sequence.getNext();

        assertEquals(Integer.MAX_VALUE, maxValue);
        assertEquals(0, valueAfterMaxValue);
    }
}
