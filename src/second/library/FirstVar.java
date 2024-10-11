package second.library;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                String epsilon = String.valueOf(selectedAccured.getPrecision());
                double returnRef = calculateReferenceValue(Double.parseDouble(valueX.getText()));
                if (returnRef < 0) {
                    valueX.setText("");
                }
                referenceValue.setText(String.format("%." + epsilon + "f", returnRef));

                double resultM = 0;
                int N = 0;
                int epsiloM = (int) selectedAccured.getPrecision();
                double term = Double.POSITIVE_INFINITY;
                while (Math.abs(term) > Math.pow(10, -epsiloM)) {
                    term = macloren(N, Double.parseDouble(valueX.getText()));
                    resultM += term;
                    N++;
                }
                maclaurinRow.setText(String.format("%." + epsilon + "f", resultM));
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
        valueX.setText("0.99999");
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


    private double calculateMaclaurinValue(double x) {
        double result = 0;
        if (x < 1 && x > -1) {
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
        //return (Math.pow(-1, n) * getFactorial(2 * n)) / ((1 - 2 * n) * getFactorial(n) ^ 2 * 4 ^ n);
        return ((Math.pow(-1, n) * getFactorial(2 * n)) / ((1 - 2 * n) * Math.pow(getFactorial(n), 2) * Math.pow(4, n))) * Math.pow(x, n);
    }




}
