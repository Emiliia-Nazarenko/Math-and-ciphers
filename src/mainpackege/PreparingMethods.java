package mainpackege;

import java.util.regex.Pattern;

public class PreparingMethods {

    public static String cleanText(String str){
        return str.replaceAll("\\p{Punct}|\\s", "").toLowerCase();
    }

    public static boolean isLetters(String str){
        return Pattern.matches("[a-zA-Z]+", str);
    }
}
