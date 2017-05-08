package com.something.dragos.myfirstapp.StatCalculators;
import java.util.*;

/**
 * Created by Dragos on 1/19/17.
 *
 * Defines a group of calculators, some will calculate statistical values
 * using the mean, some will require the data sample to be placed in ascending order
 * All of them will be a type of StatCalculator:
 */


public interface StatCalculator {

    /**
     * All statCalculators will take in a data sample, and perform some type
     * of calculation, returning a result
     *
     * @param numbers: the data sample
     * @return: the result
     */
    public double calculate(List<Double> numbers);


    /**
     *
     * @param numbers
     * @return
     */
    public String showWorking(List<Double> numbers);


    /**
     * All statCalculators will give a breif description of what type of calculation
     * was performed on the users input data sample
     *
     * @return a description
     */
    public String giveDescriptionOfCalculation();

    /**
     * All statCalculators will also give a brief description of what the calculation
     * means (i.e what it says about the users input data sample)
     * @return
     */
    public String giveMeaningOfCalculation();



}
