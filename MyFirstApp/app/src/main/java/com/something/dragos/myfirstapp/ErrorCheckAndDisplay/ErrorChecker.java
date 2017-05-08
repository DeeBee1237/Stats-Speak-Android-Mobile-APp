package com.something.dragos.myfirstapp.ErrorCheckAndDisplay;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class will be responsible for Checking that the users input was valid
 * Created by Dragos on 1/21/17.
 */

public class ErrorChecker {

    /**
     *
     * @param userInput
     * @return
     */
    public boolean checkForEmptyInput (String userInput) {
        return userInput.trim().isEmpty();
    }

    /**
     * @param userInput
     * @return
     */
    public boolean checkForSingleNumber (String userInput) {

        // if the string can be parsed as a double then it is a single number
        try {
            Double.parseDouble(userInput);
            return true;
        }

        // if it can't then it's not a single number:
        catch (NumberFormatException | NullPointerException ex) {
            return false;
        }
    }

    /**
     *
     *
     * Take in the users input, try and parse every double, and if there is an error
     * in the users input (Double can't be parsed from a string), return false
     * otherwise load all the numbers into the list, and return true
     *
     * @param userInput: string representing the users input
     * @param numbers: the list where all the extracted numbers will go in to
     * @return
     */
    public boolean usersInputWasValid (String userInput, ArrayList <Double> numbers) {
        // ensure the numbers array is empty every time we fill it with new ones:
        numbers.clear();

        String [] inputTextArray = userInput.split(" ");

        try {
            for (String currentString : inputTextArray) {
                if (currentString.isEmpty())
                    continue;
                numbers.add(Double.parseDouble(currentString.trim()));
            }

            // a fix for the crash on android version 6.0 (the crash was for the lower and upper quartile
            // feature, with working turned on) crash was due to a concurrent modification exception being thrown when
            // calling Collections.sort() on the list of numbers, in the show working method.
            // the fix is to sort the array list before it is passed to any calculator:
            Collections.sort(numbers);
            return true;
        }

        catch (NumberFormatException ex) {
            return false;
        }
    }


}
