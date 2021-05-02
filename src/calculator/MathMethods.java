package calculator;

import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.floorMod;
import static java.lang.Math.pow;

public final class MathMethods {

    /**
     * This method calculates the greatest common divisor (GCD) of two numbers a and b.
     *
     * @param a - first number for calculation gcd.
     * @param b - second number for calculation gcd.
     * @return  - the integer value of the greatest common divisor of two numbers a and b.
     */
    public static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        a = Math.abs(a);
        b = Math.abs(b);
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        int ost;
        while (b != 0) {
            ost = a % b;
            a = b;
            b = ost;
        }
        return a;
    }

    /**
     * This method checks primality of number.
     *
     * @param a - the number to be checked for primality.
     * @return  - true - if number a is prime.
     *            false - if number a isn't prime.
     */
    public static boolean isPrime(int a) {
        if (a < 1) {
            return false;
        }
        for (int i = 1; i <= Math.sqrt(a); i++) {
            if (gcd(i, a) != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method decomposes number p into prime factors, which, when multiplied, give the original number.
     *
     * @param p - the number to be factorized.
     * @return  - the map where the key is a prime factor and the value is the power of that factor.
     * @throws NonSuitableNumberException - if number p smaller then 0.
     */
    public static Map<Integer, Integer> factorization(int p) throws NonSuitableNumberException {
        if (p < 1) {
            throw new NonSuitableNumberException("Number cannot be negative or zero!");
        }
        Map<Integer, Integer> factors = new TreeMap<>();
        int i = 2;
        while (i <= p) {
            if (isPrime(i) && (p % i == 0)) {
                if (factors.containsKey(i)) {
                    factors.put(i, factors.get(i) + 1);
                } else {
                    factors.put(i, 1);
                }
                p = p / i;
            } else {
                i++;
            }
        }
        return factors;
    }

    /**
     * This method calculates Euler's function of number n.
     *
     * @param n - the number for calculating Euler's function.
     * @return  - the integer value of Euler's function of the number n.
     * @throws NonSuitableNumberException - if the number n smaller then 1.
     */
    public static int eulerFunction(int n) throws NonSuitableNumberException {
        if (n < 1) {
            throw new NonSuitableNumberException("N cannot be negative or zero!");
        }
        if (isPrime(n)) {
            return n - 1;
        }
        int func = 1;
        Map<Integer, Integer> factors = factorization(n);
        for (Integer key : factors.keySet()) {
            func *= pow(key, factors.get(key) - 1) * (key - 1);
        }
        return func;
    }

    /**
     * This method calculates an inverse element of the number a in a ring modulo n.
     *
     * @param a - the number to be inverted.
     * @param n - the value of modulo.
     * @return  - the integer inverse element of a number a in a ring modulo n.
     * @throws NonSuitableNumberException - if n smaller then 1 or a and n are not coprime (gcd(a,n) != 1)
     */
    public static int inverseElement(int a, int n) throws NonSuitableNumberException {
        if (n < 1) {
            throw new NonSuitableNumberException("N cannot be negative!");
        }
        if (a < 0) {
            a = floorMod(a, n);
        }
        if (gcd(a, n) != 1) {
            throw new NonSuitableNumberException(a + " has no Inverse element for " + n + " !");
        }
        int b = 1;
        int x = eulerFunction(n) - 1;
        while (x > 0) {
            if (x % 2 != 0) {
                x = x - 1;
                b = floorMod((b * a), n);
            } else {
                x = x / 2;
                a = floorMod((a * a), n);
            }
        }
        return b;
    }

    /**
     * This method applies the Chinese remainder theorem for finding the solution of the system of equations.
     *
     * @param s - the array of equations: x = x mod n (type - CRTEquation) that defines the system of equations.
     * @return  - the integer array of two numbers x and n - solution of the system of equations given by s.
     * @throws NonSuitableNumberException - if modules of equations (numbers n) are not coprime.
     */
    public static int[] crt(CRTEquation[] s) throws NonSuitableNumberException {
        for (int i = 0; i < s.length - 1; i++) {
            for (int j = i + 1; j < s.length; j++) {
                if (gcd(s[i].getN(), s[j].getN()) != 1) {
                    throw new NonSuitableNumberException("Modules have to be coprime!");
                }
            }
        }
        int[] m = new int[s.length];
        int[] inverseM = new int[s.length];
        int M = 1;
        int x = 0;
        for (CRTEquation crtEquation : s) {
            M *= crtEquation.getN();
        }
        for (int i = 0; i < s.length; i++) {
            m[i] = M / s[i].getN();
            inverseM[i] = inverseElement(m[i], s[i].getN());
            x += m[i] * inverseM[i] * s[i].getX();
        }
        return new int[]{floorMod(x, M), M};
    }

    //Elliptic curves---------------------------------------------------------------------------------------------------
    /**
     * This method calculates discriminant of elliptic curve.
     *
     * @param a - factor a in elliptic curve y^2 = x^3 + ax + b.
     * @param b - factor b in elliptic curve y^2 = x^3 + ax + b.
     * @param n - the value of the field dimension.
     * @return  - the integer discriminant of Elliptic curve given by parameters a, b and n.
     * @throws NonSuitableNumberException - if n smaller then 1.
     */
    public static int discriminant(int a, int b, int n) throws NonSuitableNumberException {
        if (n < 1) {
            throw new NonSuitableNumberException("N cannot be negative or Zero!");
        }
        return floorMod((-4 * (int) pow(a, 3) - (27 * (int) pow(b, 2))), n);
    }

    /**
     * This method checks if the curve given by parameters a,b and n is elliptic.
     *
     * @param a - factor a in elliptic curve y^2 = x^3 + ax + b.
     * @param b - factor b in elliptic curve y^2 = x^3 + ax + b.
     * @param n - the value of the field dimension.
     * @return  - true if given curve is elliptic.
     *            false if given curve isn't elliptic.
     * @throws NonSuitableNumberException - if n smaller then 1.
     */
    public static boolean isEllipticCurve(int a, int b, int n) throws NonSuitableNumberException {
        return !(discriminant(a, b, n) == 0);
    }

    /**
     * This method counts the number of points that belong to the elliptic curve given by parameters a, b and n.
     *
     * @param a - factor a in elliptic curve y^2 = x^3 + ax + b.
     * @param b - factor b in elliptic curve y^2 = x^3 + ax + b.
     * @param n - the value of the field dimension.
     * @return  - the integer number of points that belong to the given elliptic curve.
     * @throws NonSuitableNumberException - if the curve given by parameters a, b and n isn't elliptic.
     */
    public static int countOfPoints(int a, int b, int n) throws NonSuitableNumberException {
        if (!isEllipticCurve(a, b, n)) {
            throw new NonSuitableNumberException("f(x) isn't an Elliptic Curve");
        }
        int sum = n + 1;
        int power, q, base;
        for (int i = 0; i < n; i++) {
            base = floorMod((int) (pow(i, 3) + (a * i) + b), n);
            if (MathMethods.gcd(base, n) == 1) {
                power = (n - 1) / 2;
                q = floorMod((int) pow(base, power), n);
                sum += (q == n - 1 ? -1 : q);
            }
        }
        return sum;
    }

    /**
     * This method check if the given point (x, y) belongs to the elliptic curve given by parameters a, b and n.
     *
     * @param x - the fist parameter for the point (x, y).
     * @param y - the second parameter for the point (x, y).
     * @param a - factor a in elliptic curve y^2 = x^3 + ax + b.
     * @param b - factor b in elliptic curve y^2 = x^3 + ax + b.
     * @param n - the value of the field dimension.
     * @return  - true - if given point belongs to given elliptic curve.
     *            false - if a given point doesn't belong to given Elliptic curve.
     */
    public static boolean isPointOfEllipticCurve(int x, int y, int a, int b, int n) {
        return floorMod((int) (pow(x, 3) + (a * x) + b), n) == floorMod((int) pow(y, 2), n);
    }

    /**
     * This method sums two points (x1, y1) and (x2, y2) on an elliptic curve.
     *
     * @param x1 - the fist parameter for the first point (x1, y1).
     * @param y1 - the second parameter for the first point (x1, y1).
     * @param x2 - the fist parameter for the second point (x2, y2).
     * @param y2 - the second parameter for the second point (x2, y2).
     * @param a  - factor a in elliptic curve y^2 = x^3 + ax + b.
     * @param b  - factor b in elliptic curve y^2 = x^3 + ax + b.
     * @param n  - the value of the field dimension.
     * @return   - the integer array of two values (x, y) - the result of the summing.
     * @throws NonSuitableNumberException - if given curve isn't elliptic.
     *                                    - if at least one point doesn't belong to given elliptic curve.
     */
    public static int[] addPoints(int x1, int y1, int x2, int y2, int a, int b, int n)
            throws NonSuitableNumberException {
        if (!isEllipticCurve(a, b, n)) {
            throw new NonSuitableNumberException("f(x) isn't an Elliptic Curve");
        }
        if (!isPointOfEllipticCurve(x1, y1, a, b, n)) {
            throw new NonSuitableNumberException("Point (" + x1 + "," + y1 + ") - doesn't belong to this Elliptic Curve!");
        }
        if (!isPointOfEllipticCurve(x2, y2, a, b, n)) {
            throw new NonSuitableNumberException("Point (" + x2 + "," + y2 + ") - doesn't belong to this Elliptic Curve!");
        }
        int[] answer = new int[2];
        if (x1 == 0 && y1 == 0) {
            answer[0] = x2;
            answer[1] = y2;
        } else if (x2 == 0 && y2 == 0) {
            answer[0] = x1;
            answer[1] = y1;
        } else if (x1 == x2 && (y1 != y2 || (y1 == 0 && y2 == 0))) {
            return answer;
        } else if (x1 != x2) {
            int t = ((y2 - y1) * inverseElement((x2 - x1), n));
            answer[0] = floorMod(floorMod((int) (pow(t, 2) - x1 - x2), n), n);
            answer[1] = floorMod((-y1 + (t * (x1 - answer[0]))), n);
        } else {
            int t = floorMod(((3 * (int) pow(x1, 2) + a) * inverseElement((2 * y1), n)), n);
            answer[0] = floorMod(((int) pow(t, 2) - (2 * x2)), n);
            answer[1] = floorMod((-y1 + (t * (x1 - answer[0]))), n);
        }
        return answer;
    }

    /**
     * This method multiplies point (x, y) and number k on an elliptic curve.
     *
     * @param k - the number for multiplication.
     * @param x - the fist parameter for the point (x, y).
     * @param y - the second parameter for the point (x, y).
     * @param a - factor a in elliptic curve y^2 = x^3 + ax + b.
     * @param b - factor b in elliptic curve y^2 = x^3 + ax + b.
     * @param n - the value of the field dimension.
     * @return  - the integer array of two values (x, y) - the result of the summing.
     * @throws NonSuitableNumberException - if given curve isn't elliptic.
     *                                    - if point doesn't belong to given elliptic curve.
     */
    public static int[] multiplyEllipticCurvePoints(int k, int x, int y, int a, int b, int n) throws NonSuitableNumberException {
        if (!isEllipticCurve(a, b, n)) {
            throw new NonSuitableNumberException("f(x) isn't an Elliptic Curve!");
        }
        if (!isPointOfEllipticCurve(x, y, a, b, n)) {
            throw new NonSuitableNumberException("Point (" + x + "," + y + ") - doesn't belong to this Elliptic Curve!");
        }
        int[] answer = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        if (k == 0) {
            return answer;
        }
        answer[0] = floorMod(x, n);
        answer[1] = floorMod(y, n);
        if (k == 1) {
            return answer;
        }
        for (int i = 1; i < k; i++) {
            answer = addPoints(x, y, answer[0], answer[1], a, b, n);
        }
        return answer;
    }

    /**
     * This method calculates order of point of elliptic curve.
     * @param x - the fist parameter for the point (x, y).
     * @param y - the second parameter for the point (x, y).
     * @param a - factor a in elliptic curve y^2 = x^3 + ax + b.
     * @param b - factor b in elliptic curve y^2 = x^3 + ax + b.
     * @param n - the value of the field dimension.
     * @return  - the integer value of order of point of elliptic curve.
     * @throws NonSuitableNumberException - if the given curve isn't elliptic.
     *                                    - if the point doesn't belong to given elliptic curve.
     */
    public static int ordOfPoint(int x, int y, int a, int b, int n) throws NonSuitableNumberException {
        if (!isEllipticCurve(a, b, n)) {
            throw new NonSuitableNumberException("It is not an Elliptic Curve!");
        }
        if (!isPointOfEllipticCurve(x, y, a, b, n)) {
            throw new NonSuitableNumberException("Point (" + x + "," + y + ") - doesn't belong to this Elliptic Curve!");
        }
        int[] answer = {floorMod(x, n), floorMod(y, n)};
        int ord = 1;
        while (!(answer[0] == 0 && answer[1] == 0)) {
            answer = addPoints(x, y, answer[0], answer[1], a, b, n);
            ord++;
        }
        return ord;
    }
}
