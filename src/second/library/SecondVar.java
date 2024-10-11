package second.library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondVar {
    public JPanel contentPane;
    private JTextField start;
    private JTextField end;
    private JTextField step;
    private JTextField accurance;
    private JTable result;
    private JButton exitButton;
    private JButton calculateButton;
    private DefaultTableModel tableModel;

    public SecondVar() {
        contentPane = new JPanel();
        // Инициализация модели таблицы
        String[] columnNames = {"X", "Reference Value", "Maclaurin Value"};
        tableModel = new DefaultTableModel(columnNames, 0);
        result = new JTable(tableModel);

        //result = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(result);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Инициализация кнопки
        calculateButton = new JButton("Calculate");
        exitButton = new JButton("Exit");
        // Панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calculateButton);
        buttonPanel.add(exitButton);

        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        calculateButton.addActionListener(e -> {
            int epsilon = (int) Double.parseDouble(accurance.getText());
            double min = Double.parseDouble(start.getText());
            double max = Double.parseDouble(end.getText());
            double s = Double.parseDouble(step.getText());

            if (min <= -1) {
                min = -0.999;
                start.setText("-0.999");
            }

            if (max >= 1) {
                max = 0.999;
                end.setText("0.999");
            }

            if (s >= 1) {
                s = 0.1;
                step.setText("0.1");
            }


            for (double x = min; x <= max; x += s) {
                double res = calculateFunctionValue(x, epsilon);
                double referenceResult = calculateReferenceValue(x);
                tableModel.addRow(new Object[]{x, String.valueOf(referenceResult), String.valueOf(res)});
            }


        });

        // Обработчик для кнопки "Выход"
        exitButton.addActionListener(e -> System.exit(0));

        // Инициализация других компонентов (текстовые поля и т.д.)
        start = new JTextField();
        end = new JTextField();
        step = new JTextField();
        accurance = new JTextField();

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Start:"));
        inputPanel.add(start);
        inputPanel.add(new JLabel("End:"));
        inputPanel.add(end);
        inputPanel.add(new JLabel("Step:"));
        inputPanel.add(step);
        inputPanel.add(new JLabel("Accurance:"));
        inputPanel.add(accurance);

        contentPane.add(inputPanel, BorderLayout.NORTH);
    }

    private static double calculateReferenceValue(double x) {
        return Math.sqrt(1 + x);
    }

    public static int getFactorial(int f) {
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result *= i;
        }
        return result;
    }

    static double macloren(int n, double x) {
        return (Math.pow(-1, n) * getFactorial(2 * n) * Math.pow(x, n) / ((1 - 2 * n) * Math.pow(getFactorial(n), 2) * Math.pow(4, n)));
    }

    public static double calculateFunctionValue(double x, double epsilon) {
        double fx0 = 0;
        double resultM = 0;
        int N = 0;
        double fx = macloren(N, x);
        resultM += fx;
        while (Math.abs(fx - fx0) > Math.pow(10, -epsilon)) {
            fx0 = fx;
            N++;
            fx = macloren(N, x);
            resultM += fx;
        }

        return resultM;
    }

}

