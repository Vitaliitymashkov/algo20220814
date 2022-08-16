package org.example;

import org.example.service.Algo1;
import org.example.service.util.ElementsGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    //    private static final int[] input = new int[]{1, 2, 4, 6, 7, 8, 5, 87, 8, 9, 9, 0, 0, 87, 4, 4, 3, 3, 3, 3, 23, 2323, 232323, 32424, 4};
    //    private static final int[] input = new int[]{1}; //Ends with exception
    //    private static final int[] input = new int[]{0, 1, -2147483648}; //Ends with overflow
    //    private static final int[] input = new int[]{-1073741824, 0, 1073741824}; //Ends with overflow
    //    private static final int[] input = new int[]{-1073741820, 0, 1073741820}; //Ends with overflow
    //    private static final int[] input = new int[]{0, 1, -2147483640}; //Works well
//    private static final int[] input = new int[]{-107374182, 0, 107374182}; //Works well
//    private static final int[] input = ElementsGenerator.generateSequentialPositive(1_000);
    private static final int[] input = ElementsGenerator.generateSequentialWithDecrementPositive(10_000);

    public static void main(String[] args) {
        try {
            logger.info(Arrays.toString(new Algo1().withSkipNullResultFlag(true).findClosestFiguresIndexes(input)));
        } catch (RuntimeException e) {
            logger.info("Exception: " + e.getMessage());
        }
    }
}
