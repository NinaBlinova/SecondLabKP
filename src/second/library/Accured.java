package second.library;

public enum Accured {
    ONE(1),      // 1 число после запятой
    TWO(2),      // 2 числа после запятой
    THREE(3),    // 3 числа после запятой
    TEN(10),     // 10 чисел после запятой
    TWENTY(20);  // 20 чисел после запятой

    private int precision;

    Accured(int precision) {
        this.precision = precision;
    }

    public int getPrecision() {
        return precision;
    }
}
