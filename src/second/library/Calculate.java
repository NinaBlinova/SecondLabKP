package second.library;


public class Calculate {
    double x;
    double epsilon;
    int n;

    public Calculate(double x, double epsilon, int n){
        this.x = x;
        this.epsilon = epsilon;
        this.n = n;
    }

    public static int getFactorial(int f) {
        int result = 1;

        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }

    static double macloren(int n, double x) {
        return (Math.pow(-1, n) * getFactorial(2 * n) * Math.pow(x, n) / ((1 - 2 * n) * Math.pow(getFactorial(n), 2) * Math.pow(4, n)));
    }
}
