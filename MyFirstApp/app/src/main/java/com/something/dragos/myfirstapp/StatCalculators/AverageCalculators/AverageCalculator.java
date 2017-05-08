package com.something.dragos.myfirstapp.StatCalculators.AverageCalculators;

import com.something.dragos.myfirstapp.StatCalculators.StatCalculator;

import java.util.*;

/**
 * This abstract class defines a specific group of statistical calculators that will all use the
 * arithemtic mean and variance to perform their calculations. The purpose of this class
 * is to avoid duplicate code (for calculating the mean and variance) in the mean,variance
 * and standard deviation classes
 *
 * 
 * Created by Dragos on 1/19/17.
 */

public abstract class AverageCalculator implements StatCalculator {

    private double mean;
    private double variance;


    /**
     * Take in an array list of numbers, representing the data sample from the users input
     * and compute the arithmetic mean of the data sample:
     *
     * @param numbers
     * @return
     */
    public double findMean(List<Double> numbers) {

        double sum = 0;
        for (Double currentNumber: numbers)
            sum += currentNumber;

        this.mean = sum/numbers.size();
        return this.mean;
    }

    /**
     * Finds the variance of the sample
     * @param numbers: the data sample
     * @return
     */
    public double findVariance (List<Double> numbers) {

        double mean = findMean(numbers);

        double sum = 0;

        for (Double currentNumber: numbers)
            sum += (currentNumber - mean)*(currentNumber - mean);

        this.variance = sum/numbers.size();
        return this.variance;
    }


    /**
     * Returns a string that will explain the step by step instructions for calculating the mean:
     * @param numbers
     * @return
     */
    public String showWorkingForMean(List<Double> numbers) {

        String explanationForMean = "• Calculate the sum of all the numbers in the data sample: "
                + "\n" + "\n";

        explanationForMean += "Sum = ";
        double sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
            if (i == numbers.size() - 1)
                explanationForMean += numbers.get(i);
            else
                explanationForMean += numbers.get(i) + " + ";
        }
        explanationForMean += "\n" + "\n";
        explanationForMean += "Sum = " + sum;
        explanationForMean += "\n" + "\n";
        explanationForMean += "• divide the Sum by the total number of values in the data sample: "
                + "\n" + "\n";

        explanationForMean += " Mean = " + "(" + sum + ")" + " ÷ " + numbers.size();
        explanationForMean += "\n" + "\n";
        explanationForMean += " Mean = " + this.mean;

        return explanationForMean;
    }

    /**
     * Returns a string that will explain the step by step instructions for calculating the variance:
     * @param numbers
     * @return
     */
    public String showWorkingForVariance (List<Double> numbers) {

        String explanationForVariance = "• First calculate the mean: " + "\n" + "\n";
        explanationForVariance += showWorkingForMean(numbers);

        explanationForVariance += "\n" + "\n";
        explanationForVariance += "• Now for the variance: for every value in the data sample, subtract the mean " +
                " value, and square the result : " + "\n" + "\n";

        // keep a list of all the '(val - mean)^2' results for later:
        ArrayList<Double> listOfSquares = new ArrayList <Double> ();

        double roundedMean = Math.round(100.0*this.mean)/100.0;

        for (Double currentNumber: numbers) {
            explanationForVariance += "( " + currentNumber  + " - " + roundedMean + " )" + "²" + " = ";
            double currentResult = (currentNumber - this.mean)*(currentNumber - this.mean);
            explanationForVariance += Math.round(100.0*currentResult)/100.0;
            listOfSquares.add(currentResult);
            explanationForVariance += "\n" + "\n";
        }

        explanationForVariance += "• now take the sum of all of those values, like so: " + "\n" + "\n";

        explanationForVariance += " sum = ";

        double sumOfListOfSquares = 0;

        for (int i = 0; i < listOfSquares.size(); i++) {

            double currentResultRounded = Math.round(100.0*listOfSquares.get(i))/100.0;

            if (i == listOfSquares.size() - 1)
                explanationForVariance += currentResultRounded;
            else
                explanationForVariance += currentResultRounded + " + ";
            sumOfListOfSquares += listOfSquares.get(i);
        }


        double sumOfListOfSquaresRounded = Math.round(100.0*sumOfListOfSquares)/100.0;

        explanationForVariance += "\n" + "\n";
        explanationForVariance += "sum = " + sumOfListOfSquaresRounded;
        explanationForVariance += "\n";

        explanationForVariance += "\n" + "\n";
        explanationForVariance += "• divide this sum by the total number of values in the data sample: ";
        explanationForVariance += "\n" + "\n";

        explanationForVariance += " Variance = " + sumOfListOfSquaresRounded + " ÷ " + numbers.size();
        explanationForVariance += "\n" + "\n";
        explanationForVariance += "Variance = " + this.variance;

        return explanationForVariance;
    }


    public double getMean () {return this.mean;}

    public double getVariance () {return this.variance;}

}
