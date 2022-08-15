package org.example.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Algo1Test {

    @Test
    public void testFindClosestFiguresIndexes_whenOnlyOneElement_thenThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            int[] input = new int[]{1};
            new Algo1().findClosestFiguresIndexes(input);
        });
        assertEquals("Not enough arguments", thrown.getMessage());
    }

    @Test
    public void testFindClosestFiguresIndexes_whenNullInput_thenThrowException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            int[] input = null;
            new Algo1().findClosestFiguresIndexes(input);
        });
        assertEquals("Null input", thrown.getMessage());
    }

    //Single test - for reference only - use parametrized tests
    @Test
    public void testFindClosestFiguresIndexes_whenTwoEqualElements_thenReturnTheirIndexes() {
        int[] input = new int[]{1, 1};
        int[] expectedResult = new int[]{0, 1};
        int[] result = new Algo1().findClosestFiguresIndexes(input);
        assertThat(String.format("Result %s doesn't match expected %s", Arrays.toString(result), Arrays.toString(expectedResult)),
                result, is(expectedResult));
    }

    @ParameterizedTest
    @MethodSource("provideValidInputsWithExtremeElementsForOverflowTest")
    public void testFindClosestFiguresIndexes_whenOverflowAppears_thenReturnRuntimeException(int[] input, int[] expected) {
        System.out.println("Expected result is: " + Arrays.toString(expected));
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            int[] result = new Algo1().findClosestFiguresIndexes(input);
        });
        assertEquals("Overflow at subtract operation: integer overflow", thrown.getMessage());
    }

    private static Stream<Arguments> provideValidInputsWithExtremeElementsForOverflowTest() {
        return Stream.of(
                Arguments.of(new int[]{0, 1, Integer.MIN_VALUE}, new int[]{0, 1}),
                Arguments.of(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, new int[]{1, 2}),
                Arguments.of(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, new int[]{0, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidInputsForEqualElementsWithResultingIndexes")
    void testFindClosestFiguresIndexes_whenValidInputsForEqualElements_shouldReturnExpectedIndexes(int[] input, int[] expected) {
        assertThat("No matches expected", new Algo1().findClosestFiguresIndexes(input), is(expected));
    }

    private static Stream<Arguments> provideValidInputsForEqualElementsWithResultingIndexes() {
        return Stream.of(
                Arguments.of(new int[]{0, 0}, new int[]{0, 1}),
                Arguments.of(new int[]{1, 2, 3, 4, 0, 0}, new int[]{4, 5}),
                Arguments.of(new int[]{-1, 2, 3, 4, 0, 0}, new int[]{4, 5}),
                Arguments.of(new int[]{-1, -1, 3, 4, 0, 0}, new int[]{0, 1}),
                Arguments.of(new int[]{-1, 0, 1, -1}, new int[]{0, 3}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 1, 0}, new int[]{0, 6}),
                Arguments.of(new int[]{0, 1, 0}, new int[]{0, 2}),
                Arguments.of(new int[]{0, 0, 0}, new int[]{0, 1}),
                Arguments.of(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE}, new int[]{0, 1}),
                Arguments.of(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, new int[]{0, 1}),
                Arguments.of(new int[]{Math.round(Integer.MIN_VALUE/4), 0, Math.round(Integer.MAX_VALUE/4)}, new int[]{0, 1}),
                //TODO Check timings - Should return right after first elements - not parse all other
                Arguments.of(new int[]{0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, new int[]{0, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidInputsForDifferentElementsWithResultingIndexes")
    void testFindClosestFiguresIndexes_whenValidInputsForDifferentElements_shouldReturnExpectedIndexes(int[] input,
                                                                                               int[] expected) {
        assertThat("Not matches expected", new Algo1().findClosestFiguresIndexes(input), is(expected));
    }

    private static Stream<Arguments> provideValidInputsForDifferentElementsWithResultingIndexes() {
        return Stream.of(
                Arguments.of(new int[]{0, 1, Integer.MAX_VALUE}, new int[]{0, 1}),
                Arguments.of(new int[]{0, 100, 50, 30, 20, 1}, new int[]{0, 5}),
                Arguments.of(new int[]{1000, 100, 50, 30, 20, 1}, new int[]{3, 4}),
                Arguments.of(new int[]{0, 1, Math.round(Integer.MIN_VALUE/2)}, new int[]{0, 1}),
                Arguments.of(new int[]{0, 1, -2147483640}, new int[]{0, 1}),
                Arguments.of(new int[]{-1, 2, 3, 4, 5, 0}, new int[]{0, 5}),
                Arguments.of(new int[]{-1, -1, -1, -1, 100, 101}, new int[]{0, 1}),
                Arguments.of(new int[]{-1, 0, 1, 2}, new int[]{0, 1}),
                Arguments.of(new int[]{11, 21, 31, 41, 51, 61, 71, 10}, new int[]{0, 7}),
                Arguments.of(new int[]{0, 10, 20, 30, 31}, new int[]{3, 4}),
                Arguments.of(new int[]{-1073741821, 0, 1073741820}, new int[]{1, 2}),
                Arguments.of(new int[]{-1073741820, 0, 1073741820}, new int[]{0, 1}),
                Arguments.of(new int[]{-1073741824, 0, 1073741824}, new int[]{0, 1}), //below is similar
                Arguments.of(new int[]{Math.round(Integer.MIN_VALUE/2), 0, Math.round(Integer.MAX_VALUE/2)}, new int[]{0, 1})//above is similar
                );
    }

    @Test
    void testFindClosestFiguresIndexes_whenWithSkipNullResultFlag_shouldSkipNulls() {
        int[] input = new int[]{1, 100};
        int[] expectedResult = new int[]{0, 1};
        int[] result = new Algo1().withSkipNullResultFlag(true).findClosestFiguresIndexes(input);
        assertThat(String.format("Result %s doesn't match expected %s", Arrays.toString(result), Arrays.toString(expectedResult)),
                result, is(expectedResult));
    }
}
