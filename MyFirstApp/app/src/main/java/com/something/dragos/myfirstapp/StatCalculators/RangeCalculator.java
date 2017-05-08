package com.something.dragos.myfirstapp.StatCalculators;

import java.util.List;
import java.util.Collections;


/**
 * A calculator responsible for calculating the range of a data sample:
 * Created by Dragos on 1/20/17.
 */

public class RangeCalculator implements StatCalculator {

    private double min;
    private double max;
    private double range;

    public double calculate(List<Double> numbers) {
        Collections.sort(numbers);
        this.min = numbers.get(0);
        this.max = numbers.get(numbers.size() - 1);
        this.range = this.max - this.min;
        return this.range;
    }

    public String giveDescriptionOfCalculation() {
        return "This is the Range of the input data sample: ";
    }

    public String giveMeaningOfCalculation() {
        return "\n" + "All of the data values lie between the minimum value: " +
                this.min + ", and the maximum value: " + this.max + ". The difference between" +
                " these values is the range " + "(" + this.range + ")";
    }

    @Override
    public String showWorking(List<Double> numbers) {
        return " No working available (the explanation is sufficient)";
    }
}
