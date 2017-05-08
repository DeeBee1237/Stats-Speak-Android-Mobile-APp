package com.something.dragos.myfirstapp.StatCalculators;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A stats calculator responsible for finding the most frequently
 * occuring value in the data set:
 * Created by Dragos on 1/20/17.
 */

public class ModeCalculator implements StatCalculator {

    private Map<Double,Integer> frequencies = new HashMap<Double,Integer>();
    private boolean allFrequenciesAreTheSame;
    private List<Double> multipleModes = new ArrayList<Double>();
    private double mode;


    /**
     * Check if there is no number that occurs more than others
     * @return
     */
    private boolean checkIfAllFrequenciesAreTheSame (List<Double> numbers) {

        // get a random frequency to check the rest of the frequencies against
        int checkAgainst = this.frequencies.get(numbers.get(0));

        for (Integer currentFreq: this.frequencies.values())
            // we found a frequency that isn't the same, so therefore
            // there is at least one different frequency:
            if (currentFreq != checkAgainst)
               return false;

        return true;
    }

    /**
     * Searches the data set to see if there are multiple modes (numbers that occur with the same
     * frequency)
     */
    private void searchForMultipleModes () {

        for (Double currentNumber: this.frequencies.keySet()) {
            if (currentNumber == this.mode)
                continue;
            if (this.frequencies.get(currentNumber) == this.frequencies.get(this.mode))
                this.multipleModes.add(currentNumber);
        }
    }

    /**
     * Iterate over the data sample, counting the frequencies of each number.
     * @param numbers
     */
    private void findFrequincies (List<Double> numbers) {

        for (Double currentNumber: numbers) {

            // visiting a number that has already been seen:
            if (this.frequencies.containsKey(currentNumber)) {
                int currentFrequency = frequencies.get(currentNumber);
                this.frequencies.put(currentNumber, currentFrequency + 1);
            }

            // visiting a new number:
            else
                this.frequencies.put(currentNumber,1);
        }

        this.allFrequenciesAreTheSame = checkIfAllFrequenciesAreTheSame(numbers);
    }

    @Override
    public double calculate(List<Double> numbers) {

        findFrequincies(numbers);

        this.mode = numbers.get(0);

        for (Double currentNumber: this.frequencies.keySet()) {
            int currentFrequency = this.frequencies.get(currentNumber);
            if (currentFrequency > this.frequencies.get(mode))
                this.mode = currentNumber;
        }

        searchForMultipleModes();

        return this.mode;
    }

    @Override
    public String giveDescriptionOfCalculation() {
        return "The mode in the data sample is: ";
    }

    @Override
    public String giveMeaningOfCalculation() {

        String allFrequencies = " ";

        for (Double currentNumber: this.frequencies.keySet()) {
            int currentFrequency = this.frequencies.get(currentNumber);
            if (currentFrequency == 1)
                allFrequencies += "Number " + currentNumber + " Occurred " + " : " + " Once " + "\n";
            else
                allFrequencies += "Number " + currentNumber + " Occurred " + " : " + currentFrequency + " times" + "\n";
            allFrequencies += "\n" + "\n";
        }

        // if there are multiple modes, state those clearly in the meaning:
        if (!this.multipleModes.isEmpty()) {
            allFrequencies += "\n" + "\n";
            allFrequencies += "As Shown by the frequency results, other modes are: ";

            for (Double currentNumber: this.multipleModes)
                allFrequencies += currentNumber + " " + "\n";
        }

        this.frequencies.clear();
        this.multipleModes.clear();

        if (this.allFrequenciesAreTheSame)
            return "The mode is the most frequently occuring value in the data sample. " +
                    "Every number occured the same number of times, so technically all "  +
                    " values are a correct value for the mode";


        return "The mode is the most frequently occuring value in the data sample " +
                " These are the frequency results of the data sample: " + "\n" + "\n" +
                allFrequencies;
    }

    @Override
    public String showWorking(List<Double> numbers) {
        return "No working available (the frequency results explain it all)";
    }
}
