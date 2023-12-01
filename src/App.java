import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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


public class App extends JPanel implements ActionListener, FocusListener {

    JLabel outputValue;
    JTextField inputValue;

    public App() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JLabel inputMessage = new JLabel("Please input the amount of money in PLN");

        this.inputValue = new JTextField();
        this.inputValue.setMaximumSize(new Dimension(100, 20));
        this.inputValue.addActionListener(this);
        this.inputValue.addFocusListener(this);

        JButton convertBtn = new JButton("Convert");
        convertBtn.setVerticalTextPosition(AbstractButton.CENTER);
        convertBtn.setActionCommand("convert");

        this.outputValue = new JLabel("Converted Value");

        // Listen for actions on convertBtn
        convertBtn.addActionListener(this);

        add(inputMessage);
        add(this.inputValue);
        add(convertBtn);
        add(this.outputValue);
    }

    public void actionPerformed(ActionEvent e) {
        if ("convert".equals(e.getActionCommand())) {
            this.outputValue.setText("Converted Value: " + String.valueOf(Integer.valueOf(this.inputValue.getText()) * 0.22) + " USD");
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
