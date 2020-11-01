package mainpackege;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class MathAndCiphers {
    private JPanel MyCrypto;
    private JTabbedPane tabbedPane1;
    //Affine Cipher------------------------------------
    private JTextField enterBAffine;
    private JTextField enterAAffine;
    private JTextArea newTextAffine;
    private JTextArea oldTextAffine;
    private JButton encryptButtonAffine;
    private JButton decryptButtonAffine;
    private JButton clearButtonAffine;
    //Caesar Cipher------------------------------------
    private JTextField enterBCaesar;
    private JTextArea oldTextCaesar;
    private JTextArea newTextCaesar;
    private JButton encryptButtonCaesar;
    private JButton decryptButtonCaesar;
    private JButton clearButtonCaesar;
    //Viegenere Cipher---------------------------------
    private JTextField keyWordField;
    private JTextArea oldTextVigenere;
    private JTextArea newTextVigenere;
    private JButton encryptButtonVigenere;
    private JButton decryptButtonVigenere;
    private JButton clearButtonVigenere;
    private JButton vigenereSquereButton;
    //Mathematical Methods-----------------------------
    //GCD----------------------------------------------
    private JTextField gcdA;
    private JTextField gcdB;
    private JTextField gcdAnswer;
    private JButton gcdButton;
    private JButton gcdClear;
    //Multiplicative inverses Element------------------
    private JTextField inversA;
    private JTextField inversN;
    private JTextField inverseAnswer;
    private JButton inverseButton;
    private JButton inversClear;
    //MOD----------------------------------------------
    private JTextField modA;
    private JTextField modN;
    private JTextField modAnswer;
    private JButton modButton;
    private JButton modClear;
    //Euler Function ----------------------------------
    private JTextField enterEulerFunc;
    private JTextField answerEulerFunc;
    private JButton calculateEulerFunct;
    private JButton clearEulerFunction;
    //Mathematical Methods2----------------------------
    //Prime Check--------------------------------------
    private JTextField enterAPrime;
    private JTextField resultPrime;
    private JButton checkPrime;
    private JButton clearPrime;
    //Factorization------------------------------------
    private JTextField enterPFactor;
    private JTextField resultFactor;
    private JButton factorize;
    private JButton clearFactor;
    //CRT----------------------------------------------
    private JTextField enterXCRT1;
    private JTextField enterNCRT1;
    private JTextField enterXCRT2;
    private JTextField enterNCRT2;
    private JTextField enterXCRT3;
    private JTextField enterNCRT3;
    private JTextField answerXCRT;
    private JTextField answerNCRT;
    private JButton solveCRT;
    private JButton clearCRT;
    //Elliptic Curves----------------------------------
    //Elliptic Curve-----------------------------------
    private JTextField enterAEllipticCurve;
    private JTextField enterBEllipticCurve;
    private JTextField enterNEllipticCurve;
    //Discriminant, Elliptic Check, Number of Points---
    private JTextField discriminant;
    private JTextField isItEllipticCurve;
    private JTextField numberOfPoints;
    private JButton calculateDiscrCheckPoints;
    //Sum of Points------------------------------------
    private JTextField x1field;
    private JTextField y1field;
    private JTextField x2field;
    private JTextField y2field;
    private JTextField x3field;
    private JTextField y3field;
    private JButton sumOfPoints;
    //Point Multiplication, Ord of Point---------------
    private JTextField kfield;
    private JTextField xfield;
    private JTextField yfield;
    private JTextField kx3field;
    private JTextField ky3field;
    private JTextField ordOfPoint;
    private JButton multOfPoints;
    private JButton clearAllEllipticCurve;

    public MathAndCiphers() {

        //Affine Cipher-------------------------------------------------------------------------------------------------
        encryptButtonAffine.addActionListener(e -> {
            try {
                int a = parseInt(enterAAffine.getText());
                int b = parseInt(enterBAffine.getText());
                String oldText = oldTextAffine.getText();
                newTextAffine.setText(Ciphers.encryptDecryptAffineCipher(a, b, oldText, Mode.ENCRYPTION));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MyCrypto, "A or B is incorrect!");
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            }
        });

        decryptButtonAffine.addActionListener(e -> {
            try {
                int a = parseInt(enterAAffine.getText());
                int b = parseInt(enterBAffine.getText());
                String oldText = oldTextAffine.getText();
                newTextAffine.setText(Ciphers.encryptDecryptAffineCipher(a, b, oldText, Mode.DECRYPTION));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MyCrypto, "A or B is incorrect!");
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            }
        });

        clearButtonAffine.addActionListener(e -> {
            oldTextAffine.setText(null);
            newTextAffine.setText(null);
            enterBAffine.setText(null);
            enterAAffine.setText(null);
        });

        //Caesar Cipher-------------------------------------------------------------------------------------------------
        encryptButtonCaesar.addActionListener(e -> {
            try {
                int b = parseInt(enterBCaesar.getText());
                String oldText = oldTextCaesar.getText();
                newTextCaesar.setText(Ciphers.encryptCaesarCipher(b, oldText));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MyCrypto, "B has to be Integer!");
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            }
        });

        decryptButtonCaesar.addActionListener(e -> {
            try {
                int b = parseInt(enterBCaesar.getText());
                String oldText = oldTextCaesar.getText();
                newTextCaesar.setText(Ciphers.decryptCaesarCipher(b, oldText));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MyCrypto, "B has to be Integer!");
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            }
        });

        clearButtonCaesar.addActionListener(e -> {
            oldTextCaesar.setText(null);
            newTextCaesar.setText(null);
            enterBCaesar.setText(null);
        });

        //Vigenere Cipher-----------------------------------------------------------------------------------------------
        encryptButtonVigenere.addActionListener(e -> {
            try {
                String keyWord = keyWordField.getText();
                String oldText = oldTextVigenere.getText();
                newTextVigenere.setText(Ciphers.encryptDecryptVigenereCipher(keyWord, oldText, Mode.ENCRYPTION));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            }
        });

        decryptButtonVigenere.addActionListener(e -> {
            try {
                String keyWord = keyWordField.getText();
                String oldText = oldTextVigenere.getText();
                newTextVigenere.setText(Ciphers.encryptDecryptVigenereCipher(keyWord, oldText, Mode.DECRYPTION));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            }
        });

        clearButtonVigenere.addActionListener(e -> {
            oldTextVigenere.setText(null);
            newTextVigenere.setText(null);
            keyWordField.setText(null);
        });

        vigenereSquereButton.addActionListener(e -> {
            BufferedImage VSQ = null;
            try {
                VSQ = ImageIO.read(new File("VSQ.png"));
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(MyCrypto, "There is no picture");
            }
            JDialog vigenereSq = new JDialog();
            vigenereSq.setTitle("Vigenere Square");
            vigenereSq.setSize(800, 680);
            JLabel picLabel = new JLabel(new ImageIcon(Objects.requireNonNull(VSQ)));
            vigenereSq.add(picLabel);
            vigenereSq.setResizable(false);
            vigenereSq.setVisible(true);

        });

        //Math Methods--------------------------------------------------------------------------------------------------
        //GCD-----------------------------------------------------------------------------------------------------------
        gcdButton.addActionListener(e -> {
            try {
                int a = parseInt(gcdA.getText());
                int b = parseInt(gcdB.getText());
                gcdAnswer.setText(Integer.toString(MathMethods.gcd(a, b)));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MyCrypto, "A and B has to be Integer!");
            }
        });

        gcdClear.addActionListener(e -> {
            gcdA.setText(null);
            gcdB.setText(null);
            gcdAnswer.setText(null);
        });

        //Inverse Element-----------------------------------------------------------------------------------------------
        inverseButton.addActionListener(e -> {
            try {
                int a = parseInt(inversA.getText());
                int n = parseInt(inversN.getText());
                inverseAnswer.setText(Integer.toString(MathMethods.inverseElement(a, n)));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MyCrypto, "A and N has to be Integer!");
            }
        });

        inversClear.addActionListener(e -> {
            inverseAnswer.setText(null);
            inversA.setText(null);
            inversN.setText(null);
        });

        //MOD-----------------------------------------------------------------------------------------------------------
        modButton.addActionListener(e -> {
            try {
                int a = parseInt(modA.getText());
                int b = parseInt(modN.getText());
                if (b < 1)
                    throw new NonSuitableNumberException("N cannot be negative or Zero!");
                modAnswer.setText(Integer.toString(Math.floorMod(a, b)));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MyCrypto, "A and N has to be Integer!");
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            }
        });

        modClear.addActionListener(e -> {
            modA.setText(null);
            modN.setText(null);
            modAnswer.setText(null);
        });

        //Euler Function

        calculateEulerFunct.addActionListener(e -> {
            try {
                int n = parseInt(enterEulerFunc.getText());
                answerEulerFunc.setText(String.valueOf(MathMethods.eulerFunction(n)));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MyCrypto, "N has to be Integer!");
            }
        });

        clearEulerFunction.addActionListener(e -> {
            enterEulerFunc.setText(null);
            answerEulerFunc.setText(null);
        });


        //Is A a Prime?
        checkPrime.addActionListener(e -> {
            try {
                int p = parseInt(enterAPrime.getText());
                resultPrime.setText(String.valueOf(MathMethods.prime(p)));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MyCrypto, "A has to be Integer!");
            }
        });

        clearPrime.addActionListener(e -> {
            resultPrime.setText(null);
            enterAPrime.setText(null);
        });

        //Factorization
        factorize.addActionListener(e -> {
            try {
                int p = parseInt(enterPFactor.getText());
                Map<Integer, Integer> map = MathMethods.factorization(p);
                StringBuilder factors = new StringBuilder();
                for (Integer k : map.keySet()) {
                    factors.append("(").append(k).append("^").append(map.get(k)).append(")*");
                }
                factors.deleteCharAt(factors.length() - 1);
                resultFactor.setText(String.valueOf(factors));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MyCrypto, "P has to be Integer!");
            }
        });

        clearFactor.addActionListener(e -> {
            resultFactor.setText(null);
            enterPFactor.setText(null);
        });

        //CRT
        solveCRT.addActionListener(e -> {
            ArrayList<CRTEquation> equations = new ArrayList<>();
            try {
                if (((!enterNCRT1.getText().isEmpty() && enterXCRT1.getText().isEmpty())
                        || (!enterNCRT2.getText().isEmpty() && enterXCRT2.getText().isEmpty())
                        || (!enterNCRT3.getText().isEmpty() && enterXCRT3.getText().isEmpty()))
                || ((enterNCRT1.getText().isEmpty() && !enterXCRT1.getText().isEmpty())
                        || (enterNCRT2.getText().isEmpty() && !enterXCRT2.getText().isEmpty())
                        || (enterNCRT3.getText().isEmpty() && !enterXCRT3.getText().isEmpty()))) {
                    throw new Exception();
                }
                if (!enterXCRT1.getText().isEmpty()) {
                    int y = parseInt(enterXCRT1.getText());
                    int p = parseInt(enterNCRT1.getText());
                    equations.add(new CRTEquation(y, p));
                }
                if (!enterXCRT2.getText().isEmpty()) {
                    int y = parseInt(enterXCRT2.getText());
                    int p = parseInt(enterNCRT2.getText());
                    equations.add(new CRTEquation(y, p));
                }
                if (!enterXCRT3.getText().isEmpty()) {
                    int y = parseInt(enterXCRT3.getText());
                    int p = parseInt(enterNCRT3.getText());
                    equations.add(new CRTEquation(y, p));
                }
                if (equations.size() == 0)
                    throw new Exception();
                CRTEquation[] arr = equations.toArray(new CRTEquation[0]);
                int[] answer = MathMethods.crt(arr);
                answerXCRT.setText(String.valueOf(answer[0]));
                answerNCRT.setText(String.valueOf(answer[1]));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(MyCrypto, "Please, enter a correct Data!");
            }
        });

        clearCRT.addActionListener(e -> {
            enterXCRT1.setText(null);
            enterXCRT2.setText(null);
            enterXCRT3.setText(null);
            answerXCRT.setText(null);
            enterNCRT1.setText(null);
            enterNCRT2.setText(null);
            enterNCRT3.setText(null);
            answerNCRT.setText(null);

        });

        //Elliptic Curve
        //Discriminant, Is it Elliptic Curve, Number of Points
        calculateDiscrCheckPoints.addActionListener(e -> {
            try {
                int a = parseInt(enterAEllipticCurve.getText());
                int b = parseInt(enterBEllipticCurve.getText());
                int n = parseInt(enterNEllipticCurve.getText());
                discriminant.setText(String.valueOf(MathMethods.discriminant(a, b, n)));
                isItEllipticCurve.setText(String.valueOf(MathMethods.isEllipticCurve(a, b, n)));
                if (MathMethods.isEllipticCurve(a, b, n))
                    numberOfPoints.setText(String.valueOf(MathMethods.numberOfPoints(a, b, n)));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(MyCrypto, "A, B and N have to be Integer!");
            } catch (ArithmeticException exception) {
                JOptionPane.showMessageDialog(MyCrypto, "N can not be Zero!");
            }
        });

        //Sum of Points
        sumOfPoints.addActionListener(e -> {
            try {
                int a = parseInt(enterAEllipticCurve.getText());
                int b = parseInt(enterBEllipticCurve.getText());
                int n = parseInt(enterNEllipticCurve.getText());
                int x1 = parseInt(x1field.getText());
                int y1 = parseInt(y1field.getText());
                int x2 = parseInt(x2field.getText());
                int y2 = parseInt(y2field.getText());
                int[] answer = MathMethods.addPoints(x1, y1, x2, y2, a, b, n);
                x3field.setText(String.valueOf(answer[0]));
                y3field.setText(String.valueOf(answer[1]));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(MyCrypto, "A, B, N and x1, y1, x2, y2 have to be Integer!");
            } catch (ArithmeticException exception) {
                JOptionPane.showMessageDialog(MyCrypto, "N can not be Zero!");
            }
        });

        //Multiplication of Points
        multOfPoints.addActionListener(e -> {
            try {
                int a = parseInt(enterAEllipticCurve.getText());
                int b = parseInt(enterBEllipticCurve.getText());
                int n = parseInt(enterNEllipticCurve.getText());
                int k = parseInt(kfield.getText());
                int x = parseInt(xfield.getText());
                int y = parseInt(yfield.getText());
                int[] answer = MathMethods.multiPoints(k, x, y, a, b, n);
                kx3field.setText(String.valueOf(answer[0]));
                ky3field.setText(String.valueOf(answer[1]));
                ordOfPoint.setText(String.valueOf(MathMethods.ordOfPoint(x, y, a, b, n)));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MyCrypto, exception.getLocalizedMessage());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(MyCrypto, "A, B, N and x1, y1, x2, y2 have to be Integer!");
            } catch (ArithmeticException exception) {
                JOptionPane.showMessageDialog(MyCrypto, "N can not be Zero!");
            }
        });

        clearAllEllipticCurve.addActionListener(e -> {
            enterAEllipticCurve.setText(null);
            enterBEllipticCurve.setText(null);
            enterNEllipticCurve.setText(null);
            discriminant.setText(null);
            isItEllipticCurve.setText(null);
            numberOfPoints.setText(null);
            x1field.setText(null);
            y1field.setText(null);
            x2field.setText(null);
            y2field.setText(null);
            x3field.setText(null);
            y3field.setText(null);
            kfield.setText("1");
            xfield.setText(null);
            yfield.setText(null);
            kx3field.setText(null);
            ky3field.setText(null);
            ordOfPoint.setText(null);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyCrypto");
        frame.setContentPane(new MathAndCiphers().MyCrypto);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);


    }
}
