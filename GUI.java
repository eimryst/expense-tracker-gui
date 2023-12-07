import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Font;

public class GUI implements ActionListener {

    private static ExpenseTracker expenseTracker = new ExpenseTracker();

    private static JFrame frame = new JFrame();
    private static JLabel appName;
    private static JLabel categoryLabel;
    private static JLabel descriptionLabel;
    private static JLabel amountLabel;

    private static JComboBox<String> category;
    static String[] categ = { "", "Health", "Entertainment", "Bills", "Miscellaneous" };

    private static String value = "";
    private static JLabel expenseValue = new JLabel();

    private static JTextField amountTextField = new JTextField();
    private static JTextField descriptionTextField = new JTextField();
    private static JLabel expenseHistory = new JLabel();

    private static String categoryInput = "";
    private static String descriptionInput = "";
    private static double amountInput = 0;

    private static void baseGUI() {
        appName = new JLabel();
        appName.setText("Expense Tracker");
        appName.setBounds(110, 10, 300, 50);
        appName.setFont(new java.awt.Font("Perpetua Titling MT", 1, 26));

        categoryLabel = new JLabel();
        categoryLabel.setText("Category: ");
        categoryLabel.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14));
        categoryLabel.setBounds(25, 60, 150, 50);

        category = new JComboBox<String>(categ);
        category.setFont(new java.awt.Font("Serif", 0, 14));
        category.setBounds(175, 70, 275, 25);

        descriptionLabel = new JLabel();
        descriptionLabel.setText("Expense Description:");
        descriptionLabel.setFont(new java.awt.Font("Serif", 1, 14));
        descriptionLabel.setBounds(25, 90, 150, 50);

        amountLabel = new JLabel();
        amountLabel.setText("Amount:");
        amountLabel.setFont(new java.awt.Font("Serif", 1, 14));
        amountLabel.setBounds(25, 125, 150, 50);

        descriptionTextField.setBounds(175, 105, 275, 25);
        amountTextField.setBounds(175, 140, 275, 25);

        expenseHistory.setText("Expenses");
        expenseHistory.setBounds(165, 170, 300, 50);
        expenseHistory.setFont(new java.awt.Font("Perpetua Titling MT", 1, 26));

        frame.setTitle("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 700);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(appName);
        frame.add(categoryLabel);
        frame.add(category);
        frame.add(descriptionLabel);
        frame.add(descriptionTextField);
        frame.add(amountLabel);
        frame.add(amountTextField);
    }

    // Communicator for ExpenseTracker Class
    private static void expenses() {
        expenseTracker.addExpense(categoryInput, amountInput, descriptionInput);
        value = expenseTracker.allExpensesGetter(value);
        System.out.println(value);

        expenseValue.setText("<html>" + value + "</html>");
        expenseValue.setFont(new java.awt.Font("Serif", 0, 14));
        expenseValue.setVerticalAlignment(SwingConstants.TOP);
        expenseValue.setHorizontalAlignment(SwingConstants.LEFT);
        expenseValue.setBounds(10, 200, 680, 500);
    }

    public static void getInput(String descriptionTextField, String amountTextField) {
        descriptionInput = descriptionTextField;
        amountInput = Double.parseDouble(amountTextField);
    }

    public static void getCategory() {
        categoryInput = (String) category.getSelectedItem();
    }

    public static void main(String[] args) {
        GUI.baseGUI();

        category.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) category.getSelectedItem();
                categoryInput = selectedCategory;

            }
        });

        descriptionTextField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) { // Will only accept if both text fields aren't empty and the amount
                // textfield has a valid double value
                if (e.getKeyCode() == KeyEvent.VK_ENTER &&
                        !descriptionTextField.getText().isEmpty()
                        && !amountTextField.getText().isEmpty()
                        && amountTextField.getText().matches("\\d+(\\.\\d*)?")) {
                    getInput(descriptionTextField.getText(), amountTextField.getText());
                    descriptionTextField.setText("");
                    amountTextField.setText("");
                    expenses();
                }
            }
        });
        amountTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER &&
                        !descriptionTextField.getText().isEmpty()
                        && !amountTextField.getText().isEmpty()
                        && amountTextField.getText().matches("\\d+(\\.\\d*)?")) {
                    getInput(descriptionTextField.getText(), amountTextField.getText());
                    descriptionTextField.setText("");
                    amountTextField.setText("");
                    expenses();
                }
            }
        });
        expenseValue.repaint();
        frame.add(expenseHistory);
        frame.add(expenseValue);
    }
}
