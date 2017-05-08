package com.something.dragos.myfirstapp.ErrorCheckAndDisplay;

import com.something.dragos.myfirstapp.StatCalculators.AverageCalculators.MeanCalculator;
import com.something.dragos.myfirstapp.StatCalculators.MedianCalculators.LowerQuartileCalculator;
import com.something.dragos.myfirstapp.StatCalculators.MedianCalculators.UpperQuartileCalculator;
import com.something.dragos.myfirstapp.StatCalculators.StatCalculator;

import java.util.ArrayList;

/**
 * Displays the analysis of a statistical calculation
 * Created by Dragos on 1/19/17.
 */

public class Displayer {

    private boolean doesDisplayWorking = false;
    private StatCalculator calculator;
    private ErrorChecker errorChecker;

    private ArrayList<Double> numbers;


    public Displayer () {
        // the default calculator will be the mean:
        this.numbers = new ArrayList<Double>();
        this.errorChecker = new ErrorChecker();
    }

    public void setCalculator (StatCalculator calculator) {
        // set the type of statistical calculator:
        this.calculator = calculator;
    }

    public void setDoesDisplayWorking (boolean value) {
        this.doesDisplayWorking = value;
    }

    public boolean doesDisplayWorking () {
        return this.doesDisplayWorking;
    }


    /**
     * Displays the result from this calculator:
     * @return
     */
    public String displayResultsOfCalculation (String sampleData) {

        // check if a radio button was not selected:
        if (this.calculator == null)
            return "Please select a calculation to perform";

        // first check if the user didn't input any numbers:
        if (this.errorChecker.checkForEmptyInput(sampleData))
            return "Please input some numbers";

        // then check if the user only typed in one number:
        if (this.errorChecker.checkForSingleNumber(sampleData))
            return "Please type in more than one number";


        // finally check if the users input was valid, in the case that
        // they typed more than one number:
        if (this.errorChecker.usersInputWasValid(sampleData,this.numbers)) {

            if ((this.calculator instanceof LowerQuartileCalculator || this.calculator instanceof  UpperQuartileCalculator) &&
                    this.numbers.size() < 4)  {
               return "for quartiles, please use 4 or more numbers ";}

            String results = calculator.giveDescriptionOfCalculation() + "\n" + "\n" + calculator.calculate(this.numbers) + "\n"
                    + "\n" + calculator.giveMeaningOfCalculation();

            if (this.doesDisplayWorking)
                return results + "\n" + "\n" + "\n" + "\n" +
                        "Working:" + "\n" + "\n" + this.calculator.showWorking(this.numbers);
            else
                return results;
        }

        else
            return "Error, unrecognized input " + "\n" +
                    " Please ensure that all the inputs are numbers each seperated by at least " +
                    " one white space. ";
    }

}
