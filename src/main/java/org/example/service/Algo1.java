package org.example.service;

import org.example.service.structure.PairOfSecondIndexAndDifference;
import org.example.service.util.Parser;
import org.example.service.util.PrinterUtil;
import org.example.service.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Algo1 {
    private static final Logger logger = LoggerFactory.getLogger(Algo1.class);

    private boolean equalElementsFlag = false;

    private boolean skipNullResultFlag = false;

    private void setFlagOfEqualElements() {
        this.equalElementsFlag = true;
    }

    public Algo1 withSkipNullResultFlag(boolean skipNullResultFlag) {
        this.skipNullResultFlag = skipNullResultFlag;
        return this;
    }

    /**
     * Algo to find two closest numbers in an input array of integers.
     * Result is an array of indexes of these numbers.
     * NOTE: Search only for the first occurrence.
     * If 2 equal numbers have been found - return their indexes and stop.
     * For differences greater than already found there is no calculation of the respective numbers
     * <p>
     * Time complexity = O(n)
     * Space complexity = O(n)
     */
    public int[] findClosestFiguresIndexes(int[] input) {
        logger.debug("INITIAL: " + Arrays.toString(input));
        new Validator().validateInput(input);

        PairOfSecondIndexAndDifference[] resultsOfComparison = processInputToGetMinimumDiffs(input);

        new PrinterUtil(skipNullResultFlag).printResults(resultsOfComparison);
        return new Parser().getMinimumDiffFromResults(resultsOfComparison);
    }

    private PairOfSecondIndexAndDifference[] processInputToGetMinimumDiffs(int[] input) {
        int length = input.length;
        PairOfSecondIndexAndDifference[] resultsOfComparison = new PairOfSecondIndexAndDifference[length - 1];

        int firstIndex = 0;
        long minDifference = Integer.MAX_VALUE;
        while (firstIndex < length) {
            logger.trace("Working with index: " + firstIndex);
            int secondIndex = firstIndex + 1;
            long difference;
            while (secondIndex < length) {
                difference = getDifferenceAndCheckForOverflow(input, firstIndex, secondIndex);

                if (checkForEquality(firstIndex, secondIndex, resultsOfComparison, difference)) {
                    break;
                }
                minDifference = getMinDifference(firstIndex, secondIndex, resultsOfComparison, minDifference, difference);
//                logger.trace("MinDiff: " + minDifference);
                secondIndex++;
//                logger.trace("SecondIndex incremented: " + secondIndex);
            }
            logger.trace("SecondIndex count finished.");
            if (equalElementsFlag) {
                break;
            }
            firstIndex++;
            logger.trace("FirstIndex incremented: " + firstIndex);
        }
        return resultsOfComparison;
    }

    private long getMinDifference(int firstIndex, int secondIndex, PairOfSecondIndexAndDifference[] resultsOfComparison,
                                  long minDifference, long difference) {
        if (Math.abs(difference) < Math.abs(minDifference)) {
            minDifference = difference;
            resultsOfComparison[firstIndex] =
                    createNewPairWithMinDiff(secondIndex, difference);
        }
        return minDifference;
    }

    private boolean checkForEquality(int firstIndex, int secondIndex, PairOfSecondIndexAndDifference[] resultsOfComparison, long difference) {
        if (difference == 0) {
            setFlagOfEqualElements();
            resultsOfComparison[firstIndex] =
                    createNewPairWithMinDiff(secondIndex, difference);
            return true;
        }
        return false;
    }

    private long getDifferenceAndCheckForOverflow(int[] input, int firstIndex, int secondIndex) {
        long difference;
        try {
            difference = Math.subtractExact(input[firstIndex], input[secondIndex]);
        } catch (ArithmeticException e) {
            throw new RuntimeException("Overflow at subtract operation: " + e.getMessage());
        }
        return difference;
    }

    private PairOfSecondIndexAndDifference createNewPairWithMinDiff(int secondIndex, long difference) {
        return new PairOfSecondIndexAndDifference()
                .withSecondIndex(secondIndex)
                .withDifference(difference);
    }
}
