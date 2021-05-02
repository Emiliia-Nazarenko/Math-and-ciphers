package calculator;

import java.util.regex.Pattern;

public class PreparingMethods {

    /**
     * This method removes any punctuation character and spaces in the string,
     * and also changes characters to lower case.
     *
     * @param str - the string to be cleaned of any punctuation character and spaces.
     * @return    - new string without any punctuation character and spaces.
     */
    public static String cleanText(String str){
        return str.replaceAll("\\p{Punct}|\\s", "").toLowerCase();
    }

    /**
     * This method checks if the string consists of letters only.
     *
     * @param str - the string to be checked.
     * @return    - true - if the string consists of letters only.
     *              false - is the string consists of not letters only.
     */
    public static boolean isLetters(String str){
        return Pattern.matches("[a-zA-Z]+", str);
    }
}
