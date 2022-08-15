package org.example.service.util;

import org.example.service.structure.PairOfSecondIndexAndDifference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    public void writeLogDebugLineWithDiffData(int index, PairOfSecondIndexAndDifference pair) {
        logger.debug(String.format("For index %s there is second index %s with diff %s", index, pair.getSecondIndex(), pair.getDifference()));
    }

    public void writeLogDebugLineIfNullIsNotSkipped(int index, boolean skipNullResultFlag) {
        if (!skipNullResultFlag) {
            logger.debug(String.format("For index %s there is second index %s with diff %s", index, "NULL", "NULL"));
        }
    }

}
