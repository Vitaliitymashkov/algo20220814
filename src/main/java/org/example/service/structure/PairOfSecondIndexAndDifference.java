package org.example.service.structure;

public class PairOfSecondIndexAndDifference {
    private int secondIndex;
    private long difference;

    public PairOfSecondIndexAndDifference withSecondIndex(int secondIndex) {
        this.secondIndex = secondIndex;
        return this;
    }

    public PairOfSecondIndexAndDifference withDifference(long difference) {
        this.difference = difference;
        return this;
    }

    public Integer getSecondIndex() {
        return this.secondIndex;
    }

    public long getDifference() {
        return this.difference;
    }
}
