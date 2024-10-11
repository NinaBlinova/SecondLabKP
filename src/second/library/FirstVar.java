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
                System.out.println(res);
                System.out.println(returnRef);
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


    public static int getFactorial(int f) {
        int result = 1;

        for (int i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }

    static double macloren(int n, double x) {
        return (Math.pow(-1, n) * getFactorial(2 * n) * Math.pow(x, n)) / ((1 - 2 * n) * getFactorial(n) * getFactorial(n) * Math.pow(4, n));
    }

    public static double calculateFunctionValue(double x, double epsilon) {
        double resultM = 0;
        int N = 0;
        double fx = 1;
        double fx0 = 0;
        resultM += fx;
        while (Math.abs(fx - fx0) > epsilon) {
            fx0 = fx;
            N++;
            fx = macloren(N, x);
            resultM += fx;
        }
        resultM += fx;
        return resultM;
    }
}
