package org.example.service.util;

import org.example.service.structure.PairOfSecondIndexAndDifference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parser {
    private static final Logger logger = LoggerFactory.getLogger(Parser.class);

    public int[] getMinimumDiffFromResults(PairOfSecondIndexAndDifference[] resultsOfComparison) {
        int j = 0;
        int firstIndexRes = 0;
        int secondIndexRes = firstIndexRes + 1;
        long minDifferenceForResult = Integer.MAX_VALUE;

        for (PairOfSecondIndexAndDifference pair : resultsOfComparison) {
            if (null != pair) {
                if (Math.abs(pair.getDifference()) < Math.abs(minDifferenceForResult)) {
                    minDifferenceForResult = pair.getDifference();
                    firstIndexRes = j;
                    secondIndexRes = pair.getSecondIndex();
                }
            }
            j++;
        }
        logger.debug(String.format("Result is: for %s there is second index %s with diff %s",
                firstIndexRes, resultsOfComparison[firstIndexRes].getSecondIndex(), resultsOfComparison[firstIndexRes].getDifference()));
        return new int[]{firstIndexRes, secondIndexRes};
    }
}
