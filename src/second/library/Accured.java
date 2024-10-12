package second.library;

public enum Accured {
    ONE(0.01),      // 1 число после запятой
    TWO(0.01),      // 2 числа после запятой
    THREE(0.001),    // 3 числа после запятой
    FIFTH(0.00001),     // 5 чисел после запятой
    EIGHT(.00000001);  // 8 чисел после запятой

    private double precision;

    Accured(double precision) {
        this.precision = precision;
    }

    public double getPrecision() {
        return precision;
    }
}
