package calculator;

/**
 * The object of this class is a modular equation of the form x = x mod n.
 */
public class CRTEquation {
    private int x;
    private int n;

    public CRTEquation(int x, int n) throws NonSuitableNumberException {
        this.x = Math.floorMod(x, n);
        if(n < 1)
            throw new NonSuitableNumberException("N can not be negative or Zero!");
        this.n = n;
    }

    public int getX() {
        return x;
    }

    public int getN() {
        return n;
    }

    @Override
    public String toString() {
        return "crtEquation: " +
                "x = " + x +
                "(mod " + n +
                ')';
    }
}
