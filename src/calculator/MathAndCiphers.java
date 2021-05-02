package calculator;

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
    private JPanel MathAndCiphersPanel;
    private JTabbedPane tabbedPane;
    //Affine cipher-----------------------------------------------------------------------------------------------------
    private JTextField fieldEnterBAffineCipher;
    private JTextField fieldEnterAAffineCipher;
    private JTextArea textAreaNewAffineCipher;
    private JTextArea textAreaOldAffineCipher;
    private JButton buttonEncryptAffineCipher;
    private JButton buttonDecryptAffineCipher;
    private JButton buttonClearAffineCipher;
    //Caesar cipher-----------------------------------------------------------------------------------------------------
    private JTextField fieldEnterBCaesarCipher;
    private JTextArea textAreaOldCaesarCipher;
    private JTextArea textAreaNewCaesarCipher;
    private JButton buttonEncryptCaesarCipher;
    private JButton buttonDecryptCaesarCipher;
    private JButton buttonClearCaesarCipher;
    //Viegenere cipher--------------------------------------------------------------------------------------------------
    private JTextField textFieldKeywordViegenereCipher;
    private JTextArea textAreaOldViegenereCipher;
    private JTextArea textAreaNewViegenereCipher;
    private JButton buttonEncryptViegenereCipher;
    private JButton buttonDecryptViegenereCipher;
    private JButton buttonClearViegenereCipher;
    private JButton buttonShowViegenereSquare;
    
    //Mathematical methods----------------------------------------------------------------------------------------------
    //GCD----------------------------------------------
    private JTextField textFieldAGCD;
    private JTextField textFieldBGCD;
    private JTextField textFieldAnswerGCD;
    private JButton buttonCalculateGCD;
    private JButton buttonClearGCD;
    //Multiplicative inverses element------------------
    private JTextField textFieldAInverseElement;
    private JTextField textFieldNInverseElement;
    private JTextField textFieldAnswerInverseElement;
    private JButton buttonAnswerInverseElement;
    private JButton buttonClearInverseElement;
    //MOD----------------------------------------------
    private JTextField textFieldAMOD;
    private JTextField textFieldNMOD;
    private JTextField textFieldAnswerMOD;
    private JButton buttonCalculateMOD;
    private JButton buttonClearMOD;
    //Euler function ----------------------------------
    private JTextField textFieldNEulerFunction;
    private JTextField textFieldAnswerEulerFunction;
    private JButton buttonCalculateEulerFunction;
    private JButton buttonClearEulerFunction;

    //Mathematical methods (2 panel)------------------------------------------------------------------------------------
    //Prime check--------------------------------------
    private JTextField textFieldAPrimeCheck;
    private JTextField textFieldAnswerPrimeCheck;
    private JButton buttonCheckPrimeCheck;
    private JButton buttonClearPrimeCheck;
    //Factorization------------------------------------
    private JTextField textFieldEnterPFactorization;
    private JTextField textFieldAnswerFactorization;
    private JButton buttonFactorizeFactorization;
    private JButton buttonClearFactorization;
    //CRT----------------------------------------------
    private JTextField textFieldEnterXCRT1;
    private JTextField textFieldEnterNCRT1;
    private JTextField textFieldEnterXCRT2;
    private JTextField textFieldEnterNCRT2;
    private JTextField textFieldEnterXCRT3;
    private JTextField textFieldEnterNCRT3;
    private JTextField textFieldAnswerXCRT;
    private JTextField textFieldAnswerNCRT;
    private JButton buttonSolveCRT;
    private JButton buttonClearCRT;
    
    //Elliptic curves---------------------------------------------------------------------------------------------------
    //Elliptic curve-----------------------------------
    private JTextField textFieldEnterAEllipticCurve;
    private JTextField textFieldEnterBEllipticCurve;
    private JTextField textFieldEnterNEllipticCurve;
    //Discriminant, elliptic check, number of points---
    private JTextField textFieldDiscriminantEllipticCurve;
    private JTextField textFieldIsItEllipticCurve;
    private JTextField textFieldNumberOfPointsEllipticCurve;
    private JButton buttonCalculateDiscriminantCheckPointsEllipticCurve;
    //Sum of points------------------------------------
    private JTextField textFieldX1;
    private JTextField textFieldY1;
    private JTextField textFieldX2;
    private JTextField textFieldY2;
    private JTextField textFieldX3;
    private JTextField textFieldY3;
    private JButton buttonSumOfPoints;
    //Point multiplication, Ord of point---------------
    private JTextField textFieldK;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldKX3;
    private JTextField textFieldKY3;
    private JTextField textFieldOrdOfPoint;
    private JButton buttonMultiplyPoints;
    private JButton buttonClearAllEllipticCurve;

    public MathAndCiphers() {

        //Affine cipher-------------------------------------------------------------------------------------------------
        buttonEncryptAffineCipher.addActionListener(e -> {
            try {
                int a = parseInt(fieldEnterAAffineCipher.getText());
                int b = parseInt(fieldEnterBAffineCipher.getText());
                String oldText = textAreaOldAffineCipher.getText();
                textAreaNewAffineCipher.setText(Ciphers.encryptAffineCipher(a, b, oldText));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "A or B is incorrect!");
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            }
        });

        buttonDecryptAffineCipher.addActionListener(e -> {
            try {
                int a = parseInt(fieldEnterAAffineCipher.getText());
                int b = parseInt(fieldEnterBAffineCipher.getText());
                String oldText = textAreaOldAffineCipher.getText();
                textAreaNewAffineCipher.setText(Ciphers.decryptAffineCipher(a, b, oldText));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "A or B is incorrect!");
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            }
        });

        buttonClearAffineCipher.addActionListener(e -> {
            textAreaOldAffineCipher.setText(null);
            textAreaNewAffineCipher.setText(null);
            fieldEnterBAffineCipher.setText(null);
            fieldEnterAAffineCipher.setText(null);
        });

        //Caesar cipher-------------------------------------------------------------------------------------------------
        buttonEncryptCaesarCipher.addActionListener(e -> {
            try {
                int b = parseInt(fieldEnterBCaesarCipher.getText());
                String oldText = textAreaOldCaesarCipher.getText();
                textAreaNewCaesarCipher.setText(Ciphers.encryptCaesarCipher(b, oldText));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "B has to be Integer!");
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            }
        });

        buttonDecryptCaesarCipher.addActionListener(e -> {
            try {
                int b = parseInt(fieldEnterBCaesarCipher.getText());
                String oldText = textAreaOldCaesarCipher.getText();
                textAreaNewCaesarCipher.setText(Ciphers.decryptCaesarCipher(b, oldText));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "B has to be Integer!");
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            }
        });

        buttonClearCaesarCipher.addActionListener(e -> {
            textAreaOldCaesarCipher.setText(null);
            textAreaNewCaesarCipher.setText(null);
            fieldEnterBCaesarCipher.setText(null);
        });

        //Vigenere cipher-----------------------------------------------------------------------------------------------
        buttonEncryptViegenereCipher.addActionListener(e -> {
            try {
                String keyWord = textFieldKeywordViegenereCipher.getText();
                String oldText = textAreaOldViegenereCipher.getText();
                textAreaNewViegenereCipher.setText(Ciphers.encryptionVigenereCipher(keyWord, oldText));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            }
        });

        buttonDecryptViegenereCipher.addActionListener(e -> {
            try {
                String keyWord = textFieldKeywordViegenereCipher.getText();
                String oldText = textAreaOldViegenereCipher.getText();
                textAreaNewViegenereCipher.setText(Ciphers.decryptionVigenereCipher(keyWord, oldText));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            }
        });

        buttonClearViegenereCipher.addActionListener(e -> {
            textAreaOldViegenereCipher.setText(null);
            textAreaNewViegenereCipher.setText(null);
            textFieldKeywordViegenereCipher.setText(null);
        });

        buttonShowViegenereSquare.addActionListener(e -> {
            BufferedImage VSQ = null;
            try {
                VSQ = ImageIO.read(new File("VSQ.png"));
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "There is no picture");
            }
            JDialog vigenereSq = new JDialog();
            vigenereSq.setTitle("Vigenere Square");
            vigenereSq.setSize(800, 680);
            JLabel picLabel = new JLabel(new ImageIcon(Objects.requireNonNull(VSQ)));
            vigenereSq.add(picLabel);
            vigenereSq.setResizable(false);
            vigenereSq.setVisible(true);

        });

        //Math methods--------------------------------------------------------------------------------------------------
        //GCD-----------------------------------------------------------------------------------------------------------
        buttonCalculateGCD.addActionListener(e -> {
            try {
                int a = parseInt(textFieldAGCD.getText());
                int b = parseInt(textFieldBGCD.getText());
                textFieldAnswerGCD.setText(Integer.toString(MathMethods.gcd(a, b)));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "A and B has to be Integer!");
            }
        });

        buttonClearGCD.addActionListener(e -> {
            textFieldAGCD.setText(null);
            textFieldBGCD.setText(null);
            textFieldAnswerGCD.setText(null);
        });

        //Inverse element-----------------------------------------------------------------------------------------------
        buttonAnswerInverseElement.addActionListener(e -> {
            try {
                int a = parseInt(textFieldAInverseElement.getText());
                int n = parseInt(textFieldNInverseElement.getText());
                textFieldAnswerInverseElement.setText(Integer.toString(MathMethods.inverseElement(a, n)));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "A and N has to be Integer!");
            }
        });

        buttonClearInverseElement.addActionListener(e -> {
            textFieldAnswerInverseElement.setText(null);
            textFieldAInverseElement.setText(null);
            textFieldNInverseElement.setText(null);
        });

        //MOD-----------------------------------------------------------------------------------------------------------
        buttonCalculateMOD.addActionListener(e -> {
            try {
                int a = parseInt(textFieldAMOD.getText());
                int b = parseInt(textFieldNMOD.getText());
                if (b < 1)
                    throw new NonSuitableNumberException("N cannot be negative or Zero!");
                textFieldAnswerMOD.setText(Integer.toString(Math.floorMod(a, b)));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "A and N has to be Integer!");
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            }
        });

        buttonClearMOD.addActionListener(e -> {
            textFieldAMOD.setText(null);
            textFieldNMOD.setText(null);
            textFieldAnswerMOD.setText(null);
        });

        //Euler function------------------------------------------------------------------------------------------------
        buttonCalculateEulerFunction.addActionListener(e -> {
            try {
                int n = parseInt(textFieldNEulerFunction.getText());
                textFieldAnswerEulerFunction.setText(String.valueOf(MathMethods.eulerFunction(n)));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "N has to be Integer!");
            }
        });

        buttonClearEulerFunction.addActionListener(e -> {
            textFieldNEulerFunction.setText(null);
            textFieldAnswerEulerFunction.setText(null);
        });

        //Prime check---------------------------------------------------------------------------------------------------
        buttonCheckPrimeCheck.addActionListener(e -> {
            try {
                int p = parseInt(textFieldAPrimeCheck.getText());
                textFieldAnswerPrimeCheck.setText(String.valueOf(MathMethods.isPrime(p)));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "A has to be Integer!");
            }
        });

        buttonClearPrimeCheck.addActionListener(e -> {
            textFieldAnswerPrimeCheck.setText(null);
            textFieldAPrimeCheck.setText(null);
        });

        //Factorization-------------------------------------------------------------------------------------------------
        buttonFactorizeFactorization.addActionListener(e -> {
            try {
                int p = parseInt(textFieldEnterPFactorization.getText());
                Map<Integer, Integer> map = MathMethods.factorization(p);
                StringBuilder factors = new StringBuilder();
                for (Integer k : map.keySet()) {
                    factors.append("(").append(k).append("^").append(map.get(k)).append(")*");
                }
                factors.deleteCharAt(factors.length() - 1);
                textFieldAnswerFactorization.setText(String.valueOf(factors));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "P has to be Integer!");
            }
        });

        buttonClearFactorization.addActionListener(e -> {
            textFieldAnswerFactorization.setText(null);
            textFieldEnterPFactorization.setText(null);
        });

        //CRT-----------------------------------------------------------------------------------------------------------
        buttonSolveCRT.addActionListener(e -> {
            ArrayList<CRTEquation> equations = new ArrayList<>();
            try {
                if (((!textFieldEnterNCRT1.getText().isEmpty() && textFieldEnterXCRT1.getText().isEmpty())
                        || (!textFieldEnterNCRT2.getText().isEmpty() && textFieldEnterXCRT2.getText().isEmpty())
                        || (!textFieldEnterNCRT3.getText().isEmpty() && textFieldEnterXCRT3.getText().isEmpty()))
                || ((textFieldEnterNCRT1.getText().isEmpty() && !textFieldEnterXCRT1.getText().isEmpty())
                        || (textFieldEnterNCRT2.getText().isEmpty() && !textFieldEnterXCRT2.getText().isEmpty())
                        || (textFieldEnterNCRT3.getText().isEmpty() && !textFieldEnterXCRT3.getText().isEmpty()))) {
                    throw new Exception();
                }
                if (!textFieldEnterXCRT1.getText().isEmpty()) {
                    int y = parseInt(textFieldEnterXCRT1.getText());
                    int p = parseInt(textFieldEnterNCRT1.getText());
                    equations.add(new CRTEquation(y, p));
                }
                if (!textFieldEnterXCRT2.getText().isEmpty()) {
                    int y = parseInt(textFieldEnterXCRT2.getText());
                    int p = parseInt(textFieldEnterNCRT2.getText());
                    equations.add(new CRTEquation(y, p));
                }
                if (!textFieldEnterXCRT3.getText().isEmpty()) {
                    int y = parseInt(textFieldEnterXCRT3.getText());
                    int p = parseInt(textFieldEnterNCRT3.getText());
                    equations.add(new CRTEquation(y, p));
                }
                if (equations.size() == 0)
                    throw new Exception();
                CRTEquation[] arr = equations.toArray(new CRTEquation[0]);
                int[] answer = MathMethods.crt(arr);
                textFieldAnswerXCRT.setText(String.valueOf(answer[0]));
                textFieldAnswerNCRT.setText(String.valueOf(answer[1]));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "Please, enter a correct Data!");
            }
        });

        buttonClearCRT.addActionListener(e -> {
            textFieldEnterXCRT1.setText(null);
            textFieldEnterXCRT2.setText(null);
            textFieldEnterXCRT3.setText(null);
            textFieldAnswerXCRT.setText(null);
            textFieldEnterNCRT1.setText(null);
            textFieldEnterNCRT2.setText(null);
            textFieldEnterNCRT3.setText(null);
            textFieldAnswerNCRT.setText(null);

        });

        //Elliptic curve------------------------------------------------------------------------------------------------
        //Discriminant, elliptic curve check, number of points----------------------------------------------------------
        buttonCalculateDiscriminantCheckPointsEllipticCurve.addActionListener(e -> {
            try {
                int a = parseInt(textFieldEnterAEllipticCurve.getText());
                int b = parseInt(textFieldEnterBEllipticCurve.getText());
                int n = parseInt(textFieldEnterNEllipticCurve.getText());
                textFieldDiscriminantEllipticCurve.setText(String.valueOf(MathMethods.discriminant(a, b, n)));
                textFieldIsItEllipticCurve.setText(String.valueOf(MathMethods.isEllipticCurve(a, b, n)));
                if (MathMethods.isEllipticCurve(a, b, n))
                    textFieldNumberOfPointsEllipticCurve.setText(String.valueOf(MathMethods.countOfPoints(a, b, n)));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "A, B and N have to be Integer!");
            } catch (ArithmeticException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "N can not be Zero!");
            }
        });

        //Sum of points-------------------------------------------------------------------------------------------------
        buttonSumOfPoints.addActionListener(e -> {
            try {
                int a = parseInt(textFieldEnterAEllipticCurve.getText());
                int b = parseInt(textFieldEnterBEllipticCurve.getText());
                int n = parseInt(textFieldEnterNEllipticCurve.getText());
                int x1 = parseInt(textFieldX1.getText());
                int y1 = parseInt(textFieldY1.getText());
                int x2 = parseInt(textFieldX2.getText());
                int y2 = parseInt(textFieldY2.getText());
                int[] answer = MathMethods.addPoints(x1, y1, x2, y2, a, b, n);
                textFieldX3.setText(String.valueOf(answer[0]));
                textFieldY3.setText(String.valueOf(answer[1]));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "A, B, N and x1, y1, x2, y2 have to be Integer!");
            } catch (ArithmeticException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "N can not be Zero!");
            }
        });

        //Multiplication of points--------------------------------------------------------------------------------------
        buttonMultiplyPoints.addActionListener(e -> {
            try {
                int a = parseInt(textFieldEnterAEllipticCurve.getText());
                int b = parseInt(textFieldEnterBEllipticCurve.getText());
                int n = parseInt(textFieldEnterNEllipticCurve.getText());
                int k = parseInt(textFieldK.getText());
                int x = parseInt(textFieldX.getText());
                int y = parseInt(textFieldY.getText());
                int[] answer = MathMethods.multiplyEllipticCurvePoints(k, x, y, a, b, n);
                textFieldKX3.setText(String.valueOf(answer[0]));
                textFieldKY3.setText(String.valueOf(answer[1]));
                textFieldOrdOfPoint.setText(String.valueOf(MathMethods.ordOfPoint(x, y, a, b, n)));
            } catch (NonSuitableNumberException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, exception.getLocalizedMessage());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "A, B, N and x1, y1, x2, y2 have to be Integer!");
            } catch (ArithmeticException exception) {
                JOptionPane.showMessageDialog(MathAndCiphersPanel, "N can not be Zero!");
            }
        });

        buttonClearAllEllipticCurve.addActionListener(e -> {
            textFieldEnterAEllipticCurve.setText(null);
            textFieldEnterBEllipticCurve.setText(null);
            textFieldEnterNEllipticCurve.setText(null);
            textFieldDiscriminantEllipticCurve.setText(null);
            textFieldIsItEllipticCurve.setText(null);
            textFieldNumberOfPointsEllipticCurve.setText(null);
            textFieldX1.setText(null);
            textFieldY1.setText(null);
            textFieldX2.setText(null);
            textFieldY2.setText(null);
            textFieldX3.setText(null);
            textFieldY3.setText(null);
            textFieldK.setText("1");
            textFieldX.setText(null);
            textFieldY.setText(null);
            textFieldKX3.setText(null);
            textFieldKY3.setText(null);
            textFieldOrdOfPoint.setText(null);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyCrypto");
        frame.setContentPane(new MathAndCiphers().MathAndCiphersPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
