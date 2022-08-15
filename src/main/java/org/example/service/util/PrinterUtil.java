package org.example.service.util;

import org.example.service.Algo1;
import org.example.service.structure.PairOfSecondIndexAndDifference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrinterUtil {

    private static final Logger logger = LoggerFactory.getLogger(PrinterUtil.class);

    private final boolean skipNullResultFlag;

    public PrinterUtil(boolean skipNullResultFlag) {
        this.skipNullResultFlag = skipNullResultFlag;
    }

    public void printResults(PairOfSecondIndexAndDifference[] resultsOfComparison) {
        if (skipNullResultFlag) {
            logger.debug("NULLs have been skipped");
        }
        for (int index = 0; index < resultsOfComparison.length; index++) {
            PairOfSecondIndexAndDifference pair = resultsOfComparison[index];
            writeDebugInfo(index, pair);
        }
    }

    private void writeDebugInfo(int index, PairOfSecondIndexAndDifference pair) {
        if (null == pair) {
            new LoggerUtil().writeLogDebugLineIfNullIsNotSkipped(index, skipNullResultFlag);
        } else {
            new LoggerUtil().writeLogDebugLineWithDiffData(index, pair);
        }
    }
}
