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
                String inputText = valueX.getText();
                double xValue;

                try {
                    // Попытка преобразования введенного текста в число
                    xValue = Double.parseDouble(inputText);

                    // Проверка на диапазон
                    if (xValue >= 1) {
                        valueX.setText("0.9");
                        return; // Завершаем выполнение, если значение вне диапазона
                    }
                    if (xValue <= -1) {
                        valueX.setText("-0.9");
                        return; // Завершаем выполнение, если значение вне диапазона
                    }

                } catch (NumberFormatException ex) {
                    // Обработка случая, когда ввод не является числом
                    JOptionPane.showMessageDialog(null, "Пожалуйста, введите корректное число!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
                    valueX.setText(""); // Очистка поля ввода
                    return; // Завершаем выполнение
                }

                Accured selectedAccured = (Accured) accuranceValue.getSelectedItem();
                assert selectedAccured != null;
                //double returnRef = calculateReferenceValue(Double.parseDouble(valueX.getText()));
                //referenceValue.setText(String.valueOf(returnRef));
                //double res = calculateFunctionValue(Double.parseDouble(valueX.getText()), selectedAccured.getPrecision());
                //maclaurinRow.setText(String.valueOf(res));
                Calculate calc = new Calculate(Double.parseDouble(valueX.getText()), selectedAccured.getPrecision());
                double referenceV = calc.calculateReferenceValue();
                referenceValue.setText(String.valueOf(referenceV));
                double res = calc.calculateFunctionValue();
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
                accuranceValue.setSelectedItem(null);
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
}
