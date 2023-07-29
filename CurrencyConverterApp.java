import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CurrencyConverterApp extends JFrame {
    private JTextField inputField;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JLabel resultLabel;

    private String[] currencies = { "USD", "EUR", "GBP", "JPY" };
    private double[] conversionRates = { 1.0, 0.85, 0.73, 110.25 };

    public CurrencyConverterApp() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Convert");
        inputField = new JTextField(10);
        fromCurrencyComboBox = new JComboBox<>(currencies);
        toCurrencyComboBox = new JComboBox<>(currencies);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("");

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(inputField.getText());
                    int fromCurrencyIndex = fromCurrencyComboBox.getSelectedIndex();
                    int toCurrencyIndex = toCurrencyComboBox.getSelectedIndex();

                    double fromCurrencyRate = conversionRates[fromCurrencyIndex];
                    double toCurrencyRate = conversionRates[toCurrencyIndex];

                    double result = amount * (toCurrencyRate / fromCurrencyRate);
                    resultLabel.setText(String.format("%.2f %s", result, currencies[toCurrencyIndex]));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputField);
        inputPanel.add(fromCurrencyComboBox);
        inputPanel.add(new JLabel("to"));
        inputPanel.add(toCurrencyComboBox);

        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(convertButton, BorderLayout.SOUTH);
        add(resultLabel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CurrencyConverterApp().setVisible(true);
            }
        });
    }
}
