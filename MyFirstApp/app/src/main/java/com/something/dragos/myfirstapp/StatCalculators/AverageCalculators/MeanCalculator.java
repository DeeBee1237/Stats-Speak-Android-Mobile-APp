package com.something.dragos.myfirstapp.StatCalculators.AverageCalculators;
import com.something.dragos.myfirstapp.StatCalculators.AverageCalculators.AverageCalculator;

import java.util.*;

/**
 * Simple class to calculate the arithmetic mean of a data sample
 * Created by Dragos on 1/19/17.
 */

public class MeanCalculator extends AverageCalculator {

    /**
     * This calculator will simply compute the arithmetic mean:
     * @param numbers: the data sample
     * @return
     */
    public double calculate(List<Double> numbers) {
        return super.findMean(numbers);
    }

    @Override
    public String giveDescriptionOfCalculation() {
        return "This is the arithmetic mean of the input data sample: ";
    }

    public String giveMeaningOfCalculation() {

        return  "The mean is a measure of average. It can be used to compare two data sets " +
                " to determine which is greater or smaller on average.";
    }

    @Override
    public String showWorking(List<Double> numbers) {
        return super.showWorkingForMean(numbers);
    }
}
