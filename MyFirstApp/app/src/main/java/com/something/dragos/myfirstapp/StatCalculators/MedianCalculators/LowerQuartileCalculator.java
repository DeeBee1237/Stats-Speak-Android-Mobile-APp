package com.something.dragos.myfirstapp.StatCalculators.MedianCalculators;

import java.util.Collections;
import java.util.List;



/**
 * Created by Dragos on 2/2/17.
 */

public class LowerQuartileCalculator extends QuartileCalculator {

    private double lowerQuartile;
    // keep a reference to the list of data below the median, for the showWorking() method:
    private List<Double> lowerList;


    @Override
    public double calculate(List<Double> numbers) {

        super.calculateMedian(numbers);

        // median in middle, or was an average?
        if (!this.medianIsAnAverage) {

            List <Double> subList = numbers.subList(0,this.indexOfMedian);

            this.lowerList = subList;

            this.lowerQuartile = calculateMedian(subList);
            return this.lowerQuartile;
        }

        // otherwise, the median was an average of two numbers:
        else {

            List<Double> subList = numbers.subList(0,this.indexOfMedian + 1);

            this.lowerList = subList;

            this.lowerQuartile = calculateMedian(subList);
            return this.lowerQuartile;
        }


    }

    @Override
    public String showWorking(List<Double> numbers) {

        String working = "• First calculate the median: " + "\n" + "\n";
        working += super.showWorkingForMedian(numbers);
        working += "\n" + "\n";
        working += "• Now that we know where the median is, we simply calculate the median of the " +
                "sorted list, below this value: ";
        working += "\n" + "\n";
        working += super.showWorkingForMedian(this.lowerList);
        working += "\n" + "\n";
        working += "• So the median of our lower list, is the lower quartile: " + this.lowerQuartile;

        return  working;
    }

    @Override
    public String giveDescriptionOfCalculation() {
        return "This is the lower quartile of the input data sample: ";
    }

    @Override
    public String giveMeaningOfCalculation() {
        return "This means that the lower 25% of the input data lies between the minimum value and the lower quartile: " +
                this.lowerQuartile;
    }
}
