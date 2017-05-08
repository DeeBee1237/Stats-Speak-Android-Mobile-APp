package com.something.dragos.myfirstapp.StatCalculators.MedianCalculators;

import com.something.dragos.myfirstapp.StatCalculators.StatCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.sort;

/**
 * For code reuse: There will be stat calculators (Median, Lower Quartile, Upper Quartile)
 * that will use the median to perform their calculate () method. They can inherit that code
 * from here, and this way there is less duplicate code:
 *
 * Created by Dragos on 2/2/17.
 */

public abstract class QuartileCalculator implements StatCalculator {

    // keep track of whether the median was exactly in the middle of the sorted data
    // or if it was the average of two values:
    protected boolean medianIsAnAverage = false;

    // track the index of the median. If the median was an average, indexOfMedian
    // will be the index position of the number. If it was an average, indexOfMedian
    // will be equal to the index of the first number of the average calculation:
    protected int indexOfMedian = 0;


    /**
     *
     * @param numbers
     * @return
     */
    public double calculateMedian(List<Double> numbers) {

        // if the size of the sample is even, we need to find the middle number:
        if (numbers.size()%2 == 0) {

            int indexOfFirstNumber = (numbers.size()/2) - 1;
            double num1 = numbers.get(indexOfFirstNumber);
            double num2 = numbers.get(indexOfFirstNumber+1);

            this.medianIsAnAverage = true;
            this.indexOfMedian = indexOfFirstNumber;

            return (num1 + num2)/2;
        }

        // if the size of the data sample is odd:
        else {

            this.medianIsAnAverage = false;
            this.indexOfMedian = (int)Math.ceil(numbers.size()/2);

            return numbers.get(this.indexOfMedian);
        }
    }



    public String showWorkingForMedian (List<Double> numbers) {

        double medianForNumbers = calculateMedian(numbers);

        String workingForMedian = "• For the median value, we must first place all data values in ascending order: "
                + "\n" + "\n";

        workingForMedian += "• Our sorted Data sample: ";


        for (int i = 0; i < numbers.size(); i ++) {

            double currentNumber = numbers.get(i);
            if (i == numbers.size() - 1)
                workingForMedian += currentNumber;

            else
                workingForMedian += currentNumber + " , ";
        }

        workingForMedian += "\n" + "\n";
        workingForMedian += "• The median is in the middle of this sorted list";
        workingForMedian += "\n" + "\n";

        // even sample:
        if (numbers.size()%2 == 0) {

            workingForMedian += "• Since the number of data values is even, we need to take the average" +
                    " of the two middle values: ";
            workingForMedian += "\n" + "\n";

            workingForMedian += "Median = "  + "( "+ numbers.get((numbers.size()/2) - 1) + " + "
                    + numbers.get(numbers.size()/2) + " )" + " ÷ " + 2;

            workingForMedian += "\n" + "\n";
            workingForMedian += "Median = " + medianForNumbers;
        }

        // odd sample:
        else {
            workingForMedian += "• Since the number of data values is odd, our median value is easily" +
                    " obtained as the number in the middle of our sorted data: ";
            workingForMedian += "\n" + "\n";
            workingForMedian += "Median = " + medianForNumbers;
        }

        return workingForMedian;
    }


}
