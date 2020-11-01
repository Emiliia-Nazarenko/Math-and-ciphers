package mainpackege;

import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.floorMod;
import static java.lang.Math.pow;

public final class MathMethods {
    public static int gcd(int a, int b) {
        if(a == 0)
            return b;
        if(b == 0)
            return a;
        a = Math.abs(a);
        b = Math.abs(b);
        int temp;
        if(a < b) {
            temp = a;
            a = b;
            b = temp;
        }
        int ost;
        while(b != 0) {
            ost = a % b;
            a = b;
            b = ost;
        }
        return a;
    }

    public static boolean prime(int a) {
        if(a < 1)
            return false;
        for(int i = 1; i <= Math.sqrt(a); i++) {
            if(gcd(i, a) != 1)
                return false;
        }
        return true;
    }

    public static Map<Integer, Integer> factorization(int p) throws NonSuitableNumberException {
        if(p < 1)
            throw new NonSuitableNumberException("Number cannot be negative or zero!");
        Map<Integer, Integer> factors = new TreeMap<>();
        int i = 2;
        while(i <= p) {
            if(prime(i) && (p % i == 0)) {
                if(factors.containsKey(i))
                    factors.put(i, factors.get(i) + 1);
                else
                    factors.put(i, 1);
                p = p / i;
            } else
                i++;
        }
        return factors;
    }

    public static int eulerFunction(int n) throws NonSuitableNumberException {
        if(n < 1) throw new NonSuitableNumberException("N cannot be negative or zero!");
        if(prime(n))
            return n - 1;
        int func = 1;
        Map<Integer, Integer> factors = factorization(n);
        for(Integer key : factors.keySet())
            func *= pow(key, factors.get(key) - 1) * (key - 1);
        return func;
    }

    public static int inverseElement(int a, int n) throws NonSuitableNumberException {
        if(n < 1)
            throw new NonSuitableNumberException("N cannot be negative!");
        if(a < 0)
            a = floorMod(a, n);
        if(gcd(a, n) != 1)
            throw new NonSuitableNumberException(a + " has no Inverse element for " + n + " !");
        int b = 1;
        int x = eulerFunction(n) - 1;
        while(x > 0) {
            if(x % 2 != 0) {
                x = x - 1;
                b = floorMod((b * a), n);
            } else {
                x = x / 2;
                a = floorMod((a * a), n);
            }
        }
        return b;
    }

    //Chinese remainder theorem
    public static int[] crt(CRTEquation[] s) throws NonSuitableNumberException {
        for(int i = 0; i < s.length - 1; i++) {
            for(int j = i + 1; j < s.length; j++) {
                if(gcd(s[i].getN(), s[j].getN()) != 1) {
                    throw new NonSuitableNumberException("Modules have to be coprime!");
                }
            }
        }
        int[] m = new int[s.length];
        int[] inverseM = new int[s.length];
        int M = 1;
        int x = 0;
        for(CRTEquation crtEquation : s) {
            M *= crtEquation.getN();
        }
        for(int i = 0; i < s.length; i++) {
            m[i] = M / s[i].getN();
            inverseM[i] = inverseElement(m[i], s[i].getN());
            x += m[i] * inverseM[i] * s[i].getX();
        }
        return new int[]{floorMod(x, M), M};
    }

    //Elliptic Curve----------------------------------------------------------------------------------------------------
    public static int discriminant(int a, int b, int n) throws NonSuitableNumberException {
        if(n < 1)
            throw new NonSuitableNumberException("N cannot be negative or Zero!");
        return floorMod((-4 * (int) pow(a, 3) - (27 * (int) pow(b, 2))), n);
    }

    public static boolean isEllipticCurve(int a, int b, int n) throws NonSuitableNumberException {
        return !(discriminant(a,b, n) == 0);
    }

    public static int numberOfPoints(int a, int b, int n) throws NonSuitableNumberException {
        if(!isEllipticCurve(a, b, n))
            throw new NonSuitableNumberException("f(x) isn't an Elliptic Curve");
        int sum = n + 1;
        int power, q, base;
        for(int i = 0; i < n; i++) {
            base = floorMod((int) (pow(i, 3) + (a * i) + b), n);
            if(MathMethods.gcd(base, n) == 1) {
                power = (n - 1) / 2;
                q = floorMod((int) pow(base, power), n);
                sum += (q == n - 1 ? -1 : q);
            }
        }
        return sum;
    }

    public static boolean isPointOfEllipticCurve(int x, int y, int a, int b, int n) {
        return floorMod((int) (pow(x, 3) + (a * x) + b), n) == floorMod((int) pow(y, 2), n);
    }

    public static int[] addPoints(int x1, int y1, int x2, int y2, int a, int b, int n)
            throws NonSuitableNumberException {
        if(!isEllipticCurve(a, b, n))
            throw new NonSuitableNumberException("f(x) isn't an Elliptic Curve");
        if(!isPointOfEllipticCurve(x1, y1, a, b, n))
            throw new NonSuitableNumberException("Point (" + x1 + "," + y1 + ") - doesn't belong to this Elliptic Curve!");
        if(!isPointOfEllipticCurve(x2, y2, a, b, n))
            throw new NonSuitableNumberException("Point (" + x2 + "," + y2 + ") - doesn't belong to this Elliptic Curve!");
        int[] answer = new int[2];
        if(x1 == 0 && y1 == 0) {
            answer[0] = x2;
            answer[1] = y2;
        } else if(x2 == 0 && y2 == 0) {
            answer[0] = x1;
            answer[1] = y1;
        } else if(x1 == x2 && (y1 != y2 || (y1 == 0 && y2 == 0))) {
            return answer;
        } else if(x1 != x2) {
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

    public static int[] multiPoints(int k, int x1, int y1, int a, int b, int n) throws NonSuitableNumberException {
        if(!isEllipticCurve(a, b, n))
            throw new NonSuitableNumberException("f(x) isn't an Elliptic Curve!");
        if(!isPointOfEllipticCurve(x1, y1, a, b, n))
            throw new NonSuitableNumberException("Point (" + x1 + "," + y1 + ") - doesn't belong to this Elliptic Curve!");
        int[] answer = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        if(k == 0)
            return answer;
        answer[0] = floorMod(x1, n);
        answer[1] = floorMod(y1, n);
        if(k == 1)
            return answer;
        for(int i = 1; i < k; i++)
            answer = addPoints(x1, y1, answer[0], answer[1], a, b, n);
        return answer;
    }

    public static int ordOfPoint(int x1, int y1, int a, int b, int n) throws NonSuitableNumberException {
        if(!isEllipticCurve(a, b, n))
            throw new NonSuitableNumberException("It is not an Elliptic Curve!");
        if(!isPointOfEllipticCurve(x1, y1, a, b, n))
            throw new NonSuitableNumberException("Point (" + x1 + "," + y1 + ") - doesn't belong to this Elliptic Curve!");
        int[] answer = {floorMod(x1, n), floorMod(y1, n)};
        int ord = 1;
        while(!(answer[0] == 0 && answer[1] == 0)) {
            answer = addPoints(x1, y1, answer[0], answer[1], a, b, n);
            ord++;
        }
        return ord;
    }
}
