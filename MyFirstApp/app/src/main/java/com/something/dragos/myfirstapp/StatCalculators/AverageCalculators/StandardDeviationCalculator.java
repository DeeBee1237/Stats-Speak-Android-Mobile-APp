package com.something.dragos.myfirstapp.StatCalculators.AverageCalculators;
import com.something.dragos.myfirstapp.StatCalculators.AverageCalculators.VarianceCalculator;
import com.something.dragos.myfirstapp.StatCalculators.StatCalculator;

import java.util.List;

/**
 *
 * This calculator finds the Standard Deviation of the users input data sample:
 * Created by Dragos on 1/19/17.
 */

public class StandardDeviationCalculator extends AverageCalculator {

    private double standardDeviation;
    /**
     * Override the calculate method from the variance calculator, and use the parents
     * calculate method, because the standard deviation is simply the square root
     * of the variance
     *
     * @param numbers: the data sample
     * @return
     */
    public double calculate(List<Double> numbers) {
        this.standardDeviation = Math.sqrt(super.findVariance(numbers));
        return this.standardDeviation;
    }

    @Override
    public String giveDescriptionOfCalculation() {
        return "This is the standard deviation of the input data sample: ";
    }

    public String giveMeaningOfCalculation() {

        return  "A large standard deviation indicates that the data points are further away from the" +
                " mean, while a small standard deviation indicates that the data points are " +
                "clustered close to the mean";
    }


    @Override
    public String showWorking(List<Double> numbers) {

        String workingForSD = "• To calculate the standard deviation, the mean and" +
                " variance are required: " + "\n" + "\n" + super.showWorkingForVariance(numbers);
        workingForSD += "\n" + "\n";
        workingForSD += "• Now for the standard deviation, simply take the square root of the variance: ";
        workingForSD += "\n" + "\n";

        workingForSD += "Standard Deviation = " + "√ " + super.getVariance();
        workingForSD += "\n" + "\n";
        workingForSD += " Standard Deviation = " + this.standardDeviation;

        return workingForSD;
    }
}