package com.something.dragos.myfirstapp.StatCalculators.MedianCalculators;

import com.something.dragos.myfirstapp.StatCalculators.StatCalculator;

import java.util.List;
import java.util.Collections;

import static java.util.Collections.*;

/**
 * This StatCalculator computes the median of a data sample
 * Created by Dragos on 1/19/17.
 */

public class MedianCalculator extends QuartileCalculator {

    private double median;

    /**
     * Calculates the Median of an input data sample:
     * @param numbers: the data sample
     * @return
     */
    public double calculate(List<Double> numbers) {
        this.median = super.calculateMedian(numbers);
        return this.median;
    }

    public String giveDescriptionOfCalculation() {
        return "This is the median of the input data sample: ";
    }

    public String giveMeaningOfCalculation() {
        return  "\n" + "This means that the lower " +
                "half of the data sample values lie below " + this.median + " while the upper half" +
                " of the data sample values lie above " + this.median;
    }

    @Override
    public String showWorking(List<Double> numbers) {
        return super.showWorkingForMedian(numbers);
    }


}
