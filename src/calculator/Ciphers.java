package calculator;

public final class Ciphers {
    private static String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    //Affine cipher-----------------------------------------------------------------------------------------------------
    /**
     * This method encrypts english plain string into english cipher string by Affine cipher with parameters a and b.
     *
     * @param a   - integer parameter a for Affine cipher (c = am + b mod 26).
     * @param b   - integer parameter b for Affine cipher (c = am + b mod 26).
     * @param str - the plain text.
     * @return    - english cipher string.
     * @throws NonSuitableNumberException - if a isn't coprime to 26.
     *                                    - if input string consists not only of letters.
     */
    public static String encryptAffineCipher(int a, int b, String str) throws NonSuitableNumberException {
        StringBuilder newString = new StringBuilder();
        for (char c : prepareAffineCipher(a, str))
            newString.append(ENGLISH_ALPHABET.charAt(Math.floorMod(a * ENGLISH_ALPHABET.indexOf(c) + b, 26)));
        return newString.toString();
    }

    /**
     * This method decrypts english cipher string into english plain string by Affine cipher with parameters a and b.
     *
     * @param a   - integer parameter a for Affine cipher (c = am + b mod 26, gcd(a, 26) = 1).
     * @param b   - integer parameter b for Affine cipher (c = am + b mod 26).
     * @param str - the cipher text.
     * @return    - english cipher string.
     * @throws NonSuitableNumberException - if a isn't coprime to 26.
     *                                    - if input string consists not only of letters.
     */
    public static String decryptAffineCipher(int a, int b, String str) throws NonSuitableNumberException {
        StringBuilder newString = new StringBuilder();
        for (char c : prepareAffineCipher(a, str))
            newString.append(ENGLISH_ALPHABET.charAt(Math.floorMod(MathMethods.inverseElement(a, 26)
                    * (ENGLISH_ALPHABET.indexOf(c) - b), 26)));
        return newString.toString();
    }

    /**
     * This method checks the input string for non-alphabetic characters, removes punctuation and spaces,
     * and converts it to lowercase. And also the parameter a will be checked for correctness.
     *
     * @param a   - integer parameter a for Affine cipher (c = am + b mod 26, gcd(a, 26) = 1).
     * @param str - input string for checking.
     * @return    - char array with characters of input string.
     * @throws NonSuitableNumberException - if a isn't coprime to 26.
     *                                    - if input string consists not only of letters.
     */
    private static char[] prepareAffineCipher(int a, String str) throws NonSuitableNumberException {
        str = PreparingMethods.cleanText(str);
        if (a < 0)
            a = Math.floorMod(a, 26);
        if (MathMethods.gcd(a, 26) != 1)
            throw new NonSuitableNumberException("A has to be coprime with 26!");
        if (!PreparingMethods.isLetters(str))
            throw new NonSuitableNumberException("Enter a correct text, please!");
        return str.toCharArray();
    }

    //Caesar cipher-----------------------------------------------------------------------------------------------------
    /**
     * This method encrypts english plain string into english cipher string by Caesar cipher with parameter b.
     *
     * @param b   - integer parameter b for Caesar cipher (c = m + b mod 26).
     * @param str - the plain text.
     * @return    - english cipher string.
     * @throws NonSuitableNumberException - if input string consists not only of letters.
     */
    public static String encryptCaesarCipher(int b, String str) throws NonSuitableNumberException {
        return encryptAffineCipher(1, b, str);
    }

    /**
     * This method decrypts english cipher string into english plain string by Caesar cipher with parameter b.
     *
     * @param b   - integer parameter b for Caesar cipher (c = m + b mod 26).
     * @param str - the cipher text.
     * @return    - english plain string.
     * @throws NonSuitableNumberException - if input string consists not only of letters.
     */
    public static String decryptCaesarCipher(int b, String str) throws NonSuitableNumberException {
        return decryptAffineCipher(1, b, str);
    }

    // Vigenere cipher--------------------------------------------------------------------------------------------------
    /**
     * This method encrypts english plain string into english cipher string by Vigenere cipher with key word.
     *
     * @param keyWord - the string key word for encryption.
     * @param str     - the plain text.
     * @return        - english cipher string.
     * @throws NonSuitableNumberException - if input string or key word consists not only of letters.
     */
    public static String encryptionVigenereCipher(String keyWord, String str)
            throws NonSuitableNumberException {
        preparingVigenereCipher(keyWord, str);
        StringBuilder newString = new StringBuilder();
        for (int j = 0; j < str.length(); j++) {
            int sw = ENGLISH_ALPHABET.indexOf(str.charAt(j));
            int k = ENGLISH_ALPHABET.indexOf(keyWord.charAt(j % keyWord.length()));
            newString.append(ENGLISH_ALPHABET.charAt((sw + k) % 26));
        }
        return newString.toString();
    }

    /**
     * This method decrypts english cipher string into english plain string by Vigenere cipher with key word.
     *
     * @param keyWord - the string key word for decryption.
     * @param str     - the cipher text.
     * @return        - english plain string.
     * @throws NonSuitableNumberException - if input string or key word consists not only of letters.
     */
    public static String decryptionVigenereCipher(String keyWord, String str)
            throws NonSuitableNumberException {
        preparingVigenereCipher(keyWord, str);
        StringBuilder newString = new StringBuilder();
        for (int j = 0; j < str.length(); j++) {
            int sw = ENGLISH_ALPHABET.indexOf(str.charAt(j));
            int k = ENGLISH_ALPHABET.indexOf(keyWord.charAt(j % keyWord.length()));
            newString.append(ENGLISH_ALPHABET.charAt(Math.floorMod((sw - k), 26)));
        }
        return newString.toString();
    }

    /**
     * This method checks the input string and key word for non-alphabetic characters, removes punctuation and spaces,
     * and converts it to lowercase.
     *
     * @param keyWord - the string key word for checking.
     * @param str     - input string for checking.
     * @throws NonSuitableNumberException - if input string or key word consists not only of letters.
     */
    private static void preparingVigenereCipher(String keyWord, String str) throws NonSuitableNumberException {
        keyWord = keyWord.toLowerCase();
        str = PreparingMethods.cleanText(str);
        if (!PreparingMethods.isLetters(keyWord))
            throw new NonSuitableNumberException("Key word has to consist of letters only!");
        if (!PreparingMethods.isLetters(str))
            throw new NonSuitableNumberException("Enter a correct text, please!");
    }
}







