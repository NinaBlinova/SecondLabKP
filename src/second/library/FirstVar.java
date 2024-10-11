package second.library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import second.library.Calculate;

public class FirstVar {
    public JPanel contentPane;
    private JTextField valueX;
    private JButton claculationButton;
    private JButton resetValuesButton;
    private JButton exitButton;
    private JButton alternativeInputButton;
    private JLabel maclaurinRow;
    private JLabel referenceValue;
    private JComboBox accuranceValue;


    public FirstVar() {
        // Настройка элементов интерфейса
        claculationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Accured selectedAccured = (Accured) accuranceValue.getSelectedItem();
                assert selectedAccured != null;
                double returnRef = calculateReferenceValue(Double.parseDouble(valueX.getText()));
                referenceValue.setText(String.valueOf(returnRef));
                double res = calculateFunctionValue(Double.parseDouble(valueX.getText()), selectedAccured.getPrecision());
                maclaurinRow.setText(String.valueOf(res));
            }
        });

        resetValuesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // сброс значений
                valueX.setText("");
                maclaurinRow.setText("");
                referenceValue.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        alternativeInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Second Variable Calculation");
                frame.setContentPane(new SecondVar().contentPane);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null); // Центрирование окна
            }
        });
        populateGenreComboBox();
        valueX.setText("0.5");
    }


    private void populateGenreComboBox() {
        // Get the combo box model
        DefaultComboBoxModel<Accured> comboBoxModel = (DefaultComboBoxModel<Accured>) accuranceValue.getModel();

        // Add genre values to the combo box model
        for (Accured a : Accured.values()) {
            comboBoxModel.addElement(a);
        }
    }

    private double calculateReferenceValue(double x) {
        double result = 0;
        if (x <= 1 && x >= -1) {
            result = Math.sqrt(1 + x);
        } else {
            result = -1;
        }
        return result;
    }


    public static double getFactorial(int f) {
        double result = 1;

        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }


    public static double getPow(double x, int y) {
        double res = 1;
        for (int i = 1; i <= y; i++) {
            res *= x;
        }
        return res;
    }

    static double maclorenNumerator(int n) {
        return (getPow(-1, n) * getFactorial(2 * n));
    }

    static double maclorenDenominator(int n) {
        return (1 - 2 * n) * getPow(getFactorial(n), 2) * getPow(4, n);
    }

    static double macloren(int n, double x) {
        return (maclorenNumerator(n) / maclorenDenominator(n)) * getPow(x, n);
    }

    public static double calculateFunctionValue(double x, double epsilon) {
        double result = 0;
        int n = 0;
        double fx;

        do {
            fx = macloren(n, x);
            result += fx;
            n++;
            //System.out.println("n: " + n + ", term: " + fx + ", result: " + result);
        } while (Math.abs(fx) > epsilon);

        return result;
    }
}
