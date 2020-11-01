package mainpackege;

public final class Ciphers {
    private static String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    //Affine Cipher-----------------------------------------------------------------------------------------------------
    public static String encryptDecryptAffineCipher(int a, int b, String str, Mode mode)
            throws NonSuitableNumberException {
        str = PreparingMethods.cleanText(str);
        if(a < 0)
            a = Math.floorMod(a, 26);
        if(MathMethods.gcd(a, 26) != 1)
            throw new NonSuitableNumberException("A has to be coprime with 26!");
        if(!PreparingMethods.isLetters(str))
            throw new NonSuitableNumberException("Enter a correct text, please!");
        StringBuilder newString = new StringBuilder();
        char[] charArray = str.toCharArray();
        if(mode == Mode.ENCRYPTION) {
            for(char c : charArray)
                newString.append(ENGLISH_ALPHABET.charAt(Math.floorMod(a * ENGLISH_ALPHABET.indexOf(c) + b, 26)));
        } else {
            for(char c : charArray)
                newString.append(ENGLISH_ALPHABET.charAt(Math.floorMod(MathMethods.inverseElement(a, 26)
                        * (ENGLISH_ALPHABET.indexOf(c) - b), 26)));
        }
        return newString.toString();
    }

    //Caesar Cipher-----------------------------------------------------------------------------------------------------
    public static String encryptCaesarCipher(int b, String str) throws NonSuitableNumberException {
        return encryptDecryptAffineCipher(1, b, str, Mode.ENCRYPTION);
    }

    public static String decryptCaesarCipher(int b, String str) throws NonSuitableNumberException {
        return encryptDecryptAffineCipher(1, b, str, Mode.DECRYPTION);
    }

    // Vigenere Cipher--------------------------------------------------------------------------------------------------
    public static String encryptDecryptVigenereCipher(String keyWord, String str, Mode mod)
            throws NonSuitableNumberException {
        keyWord = keyWord.toLowerCase();
        str = PreparingMethods.cleanText(str);
        if(!PreparingMethods.isLetters(keyWord))
            throw new NonSuitableNumberException("Key word has to consist of letters only!");
        if(!PreparingMethods.isLetters(str))
            throw new NonSuitableNumberException("Enter a correct text, please!");
        StringBuilder newString = new StringBuilder();
        for(int j = 0; j < str.length(); j++) {
            int sw = ENGLISH_ALPHABET.indexOf(str.charAt(j));
            int k = ENGLISH_ALPHABET.indexOf(keyWord.charAt(j % keyWord.length()));
            if(mod == Mode.ENCRYPTION)
                newString.append(ENGLISH_ALPHABET.charAt((sw + k) % 26));
            else
                newString.append(ENGLISH_ALPHABET.charAt(Math.floorMod((sw - k), 26)));
        }
        return newString.toString();
    }
}
