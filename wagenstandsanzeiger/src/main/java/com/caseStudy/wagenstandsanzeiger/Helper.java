package com.caseStudy.wagenstandsanzeiger;



/**
 * Class Helper contains methods that assist the program but are not the main goal of the application.
 **/

public class Helper {


    /**
     * checks if a string contains positive whole numbers
     * @param value contains the string which should be checked
     * @return - true if string contains digits, false if not
     **/
    public static boolean isPositiveWholeNumber(String value) {
        try {
            double num = Double.parseDouble(value);
            return num > 0 && num == Math.floor(num);
        } catch (NumberFormatException e) {
            return false; // Unable to parse as a valid number
        }
    }
}
