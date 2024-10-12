package second.library;


public class Calculate {
    double x;
    double epsilon;


    public Calculate(double x, double epsilon) {
        this.x = x;
        this.epsilon = epsilon;
    }

    double calculateReferenceValue() {
        double result = 0;
        if (this.x <= 1 && this.x >= -1) {
            result = Math.sqrt(1 + this.x);
        } else {
            result = -1;
        }
        return result;
    }


    public static double getFactorial(double f) {
        double result = 1;

        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }


    public static double getPow(double x, double y) {
        double res = 1;
        for (int i = 1; i <= y; i++) {
            res *= x;
        }
        return res;
    }

    double maclorenNumerator(double n) {
        return (getPow(-1, n) * getFactorial(2 * n));
    }

    double maclorenDenominator(double n) {
        return (1 - 2 * n) * getPow(getFactorial(n), 2) * getPow(4, n);
    }

    double macloren(double n) {
        return (maclorenNumerator(n) / maclorenDenominator(n)) * getPow(this.x, n);
    }

    public double calculateFunctionValue() {
        double result = 0;
        double n = 0;
        double fx;

        do {
            fx = macloren(n);
            result += fx;
            n++;
            //System.out.println("n: " + n + ", term: " + fx + ", result: " + result);
        } while (Math.abs(fx) > this.epsilon);

        return result;
    }
}
