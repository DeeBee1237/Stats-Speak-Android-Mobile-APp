package com.something.dragos.myfirstapp.StatCalculators.MedianCalculators;

import java.util.List;

/**
 * Created by Dragos on 2/2/17.
 */

public class UpperQuartileCalculator extends QuartileCalculator {

    private double upperQuartile;
    // keep a reference to the list of data above the median, for the showWorking() method:
    private List<Double> upperList;


    @Override
    public double calculate(List<Double> numbers) {

        calculateMedian(numbers);

        List <Double> subList = numbers.subList(this.indexOfMedian + 1,numbers.size());

        this.upperList = subList;

        this.upperQuartile = calculateMedian(subList);
        return this.upperQuartile;
    }

    @Override
    public String showWorking(List<Double> numbers) {
        String working = "• First calculate the median: " + "\n" + "\n";
        working += super.showWorkingForMedian(numbers);
        working += "\n" + "\n";
        working += "• Now that we know where the median is, we simply calculate the median of the " +
                "sorted list, above this value: ";
        working += "\n" + "\n";
        working += super.showWorkingForMedian(this.upperList);
        working += "\n" + "\n";
        working += "• So the median of our upper list, is the upper quartile: " + this.upperQuartile;

        return  working;
    }

    @Override
    public String giveDescriptionOfCalculation() {
        return "This is the upper quartile of the input data sample: ";
    }

    @Override
    public String giveMeaningOfCalculation() {
        return "This means that the upper 25% of the input data sample, lies between: " + this.upperQuartile + " and the" +
                " maximum value.";
    }
}
