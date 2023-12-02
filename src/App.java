import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class App extends JPanel implements ActionListener, FocusListener {

    JLabel outputValue, inputMessage;
    JTextField inputValue;
    JComboBox currencyChooserStart, currencyChooserOutput;
    Currency unitedStatesDollar = new Currency("USD", 1f);
    Currency polishZloty = new Currency("PLN", 0.22f);
    Currency euro = new Currency("EUR", 0.95f);
    Currency[] currencies = {unitedStatesDollar, polishZloty, euro};

    public App() {

        

        String[] currenciesList = {unitedStatesDollar.currencyName, polishZloty.currencyName, euro.currencyName};

        JLabel inputCurrencyLabel = new JLabel("Input currency");
        inputCurrencyLabel.setBounds(10, 10, 100, 20);

        JLabel outputCurrencyLabel = new JLabel("Output currency");
        outputCurrencyLabel.setBounds(120, 10, 100, 20);

        currencyChooserStart = new JComboBox(currenciesList);
        currencyChooserStart.setBounds(10, 40, 100, 20);

        currencyChooserOutput = new JComboBox(currenciesList);
        currencyChooserOutput.setBounds(120, 40, 100, 20);

        inputMessage = new JLabel("Please input the amount of money");
        inputMessage.setBounds(10, 70, 300, 20);

        inputValue = new JTextField();
        inputValue.setBounds(10, 100, 100, 20);
        inputValue.addActionListener(this);
        inputValue.addFocusListener(this);

        JLabel choosenInputCurrency = new JLabel("USD");
        choosenInputCurrency.setBounds(120, 100, 50, 20);

        currencyChooserStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCurrency = unitedStatesDollar.currencyName;
                // Find the selected currency that was chosen in the JComboBox currencyChooserStart
                for (int i = 0; i < currencies.length; i++) {
                    if (currencyChooserStart.getSelectedItem() == currencies[i].currencyName) {
                        selectedCurrency = currencies[i].currencyName;
                        break;
                    }
                }
                choosenInputCurrency.setText(selectedCurrency);
            }
        });

        JButton convertBtn = new JButton("Convert");
        convertBtn.setVerticalTextPosition(AbstractButton.CENTER);
        convertBtn.setActionCommand("convert");
        convertBtn.setBounds(50, 130, 100, 20);

        outputValue = new JLabel("Converted Value");
        outputValue.setBounds(10, 160, 200, 20);

        // Listen for actions on convertBtn
        convertBtn.addActionListener(this);

        add(inputCurrencyLabel);
        add(outputCurrencyLabel);
        add(currencyChooserStart);
        add(currencyChooserOutput);
        add(inputMessage);
        add(inputValue);
        add(choosenInputCurrency);
        add(convertBtn);
        add(outputValue);

        setLayout(null);
        setVisible(true);
        setSize(300, 300);
    }

    public void actionPerformed(ActionEvent e) {
        if ("convert".equals(e.getActionCommand())) {
            float moneyInputValue = 0f;
            try {
                moneyInputValue = Float.parseFloat(inputValue.getText());
            } catch(Exception ex) {
                System.out.println(ex);
            }

            Currency startingCurrency = unitedStatesDollar;
            Currency resultCurrenty = unitedStatesDollar;

            // Find the selected currency that was chosen in the JComboBox currencyChooserStart
            for (int i = 0; i < currencies.length; i++) {
                if (currencyChooserStart.getSelectedItem() == currencies[i].currencyName) {
                    startingCurrency = currencies[i];
                    break;
                }
            }

            // Do the same for the currencyChooserResult
            for (int i = 0; i < currencies.length; i++) {
                if (currencyChooserOutput.getSelectedItem() == currencies[i].currencyName) {
                    resultCurrenty = currencies[i];
                    break;
                }
            }
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.FLOOR);

            outputValue.setText("Converted Value: " + String.valueOf(df.format(CurrencyConverter.convert(moneyInputValue, startingCurrency, resultCurrenty))) + " " + resultCurrenty.currencyName);         
        }
    }

    public void focusGained(FocusEvent e) {
        /*
         * To Do
         */
    }

    public void focusLost(FocusEvent e) {
        /*
         * To Do
         */
    }

    private static void createAndShowGUI() {
        // Create and setup the window
        JFrame frame = new JFrame("CurrencyConverter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 300));

        // Create and setup the content pane
        App newApp = new App();
        newApp.setOpaque(true);
        frame.setContentPane(newApp);

        // Display the window
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) throws Exception {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
