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
            double epsilon, min, max, s;

            try {
                // Попытка преобразования введенных значений в числа
                epsilon = Math.pow(10, -Double.parseDouble(accurance.getText()));
                min = Double.parseDouble(start.getText());
                max = Double.parseDouble(end.getText());
                s = Double.parseDouble(step.getText());
            } catch (NumberFormatException ex) {
                // Обработка случая, когда ввод не является числом
                JOptionPane.showMessageDialog(null, "Пожалуйста, введите корректные числа!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                start.setText("");
                end.setText("");
                step.setText("");
                accurance.setText("");
                return; // Завершаем выполнение, если ввод некорректен
            }

            if (min <= -1) {
                min = -0.9;
                start.setText("-0.9");
            }

            if (max >= 1) {
                max = 0.9;
                end.setText("0.9");
            }

            if (s >= 1) {
                s = 0.1;
                step.setText("0.1");
            }


            for (double x = min; x <= max; x += s) {
                Calculate calc = new Calculate(x, epsilon);
                double res = calc.calculateFunctionValue();
                double referenceResult = calc.calculateReferenceValue();
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

}

