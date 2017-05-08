package com.something.dragos.myfirstapp.StatCalculators.AverageCalculators;

import com.something.dragos.myfirstapp.StatCalculators.AverageCalculators.AverageCalculator;

import java.util.List;

/**
 * This Calculator will compute the variance of the users input data sample:
 * Created by Dragos on 1/19/17.
 */

public class VarianceCalculator extends AverageCalculator {

    /**
     * Finds the variance of the sample
     * @param numbers: the data sample
     * @return
     */
    public double calculate(List<Double> numbers) {
       return super.findVariance(numbers);
    }

    @Override
    public String giveDescriptionOfCalculation() {
        return "This is the variance of the input data sample: ";
    }

    public String giveMeaningOfCalculation() {
        return  "The variance is also a measure of how spread out the data is from the mean value";
    }


    @Override
    public String showWorking(List<Double> numbers) {
        return super.showWorkingForVariance(numbers);
    }
}
