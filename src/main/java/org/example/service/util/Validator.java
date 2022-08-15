package org.example.service.util;

public class Validator {

    public void validateInput(int[] input) {
        if (null == input) {
            throw new IllegalArgumentException("Null input");
        }

        if (input.length < 2) {
            throw new IllegalArgumentException("Not enough arguments");
        }
    }
}
