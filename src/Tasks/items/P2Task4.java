package Tasks.items;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.SubmitButton;
import UI.components.TextInput;

import javax.swing.*;
import java.awt.*;

class ResultFrame extends JFrame {
    private static final String[] COLUMN_NAMES = {"<html><b>Month</b></html>", "<html><b>Amount</b></html>"};
    private static final String[] ROW_NAMES = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth"};

    public ResultFrame(double monthlySavingAmount, double[] results) {
        // Inherit from parent class and set title
        super("Task 4 - Result");

        // basic properties of the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 220));
        setMinimumSize(new Dimension(500, 220));

        // Container (with padding) for the elements
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        JLabel descriptionLabel = new JLabel("<html>Shown below are the monthly balance when you deposit $" + monthlySavingAmount + " into your account every month with an annual interest rate of 3.75%.</html>", JLabel.LEFT);
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Construct the data set for the table
        String[][] data = new String[results.length][COLUMN_NAMES.length];
        for (int i = 0; i < results.length; i++) {
            data[i][0] = ROW_NAMES[i];
            data[i][1] = String.valueOf(results[i]);
        }

        // Initialize the data table
        JTable table = new JTable(data, COLUMN_NAMES);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setAlignmentX(Component.LEFT_ALIGNMENT);
        table.setMaximumSize(new Dimension(Integer.MAX_VALUE, table.getPreferredSize().height));

        // Add everything into the content container
        panel.add(descriptionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 16)));
        panel.add(new JScrollPane(table));

        // Bind the panel into the frame and display the window
        setContentPane(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

public class P2Task4 extends TaskInstance {
    private TextInput monthlySavingAmountInput;
    private final static double MONTHLY_INTEREST_RATE = 0.003125;

    public P2Task4(MainWindow window) {
        super(window);

        name = "Task 4 - Financial Application: Compound Value";
        description = "<p>Suppose you save $100 each month in a savings account with annual interest rate 3.75%. Thus, the monthly interest rate is 0.0375 / 12 = 0.003125. After the first month, the value in the account becomes</p><code>100 * (1 + 0.003125) = 100.3125</code><p>After the second month, the value in the account becomes</p><code>(100+100.3125) * (1 + 0.003125) = 200.938</code><p>After the third month, the value in the account becomes</p><code>(100 + 200.938) * (1 + 0.003125) = 301.878</code><p>and so on.</p><p>Write a program that prompts the user to enter a monthly saving amount and display the account value after six months.</p>";
    }

    // Core logic for the task
    private double[] calculateAmount(double monthlySavingAmount) {
        double[] results = {monthlySavingAmount * (1 + MONTHLY_INTEREST_RATE), 0, 0, 0, 0, 0};

        for (int i = 1; i < 6; i++) {
            results[i] = (results[i - 1] + monthlySavingAmount) * (1 + MONTHLY_INTEREST_RATE);
        }

        return results;
    }

    private void onSubmit() {
        try {
            // Parse the inputs, do the Meth, and show the result in a dialog box
            double number = Double.parseDouble(monthlySavingAmountInput.getText());
            double[] result = calculateAmount(number);

            // open a custom frame for showing the result in a table
            new ResultFrame(number, result);
        } catch (NumberFormatException e) {
            // Show error when the user input is invalid (aka not a proper double)
            JOptionPane.showMessageDialog(null, "Please enter a valid three digit number from 100 to 999.", "Number Parsing Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        JPanel inputForm = new JPanel();
        inputForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        monthlySavingAmountInput = new TextInput();
        SubmitButton submitButton = new SubmitButton(e -> onSubmit());

        // Gap between each component
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.gridy = 0;

        // Row 1: Monthly saving amount Input
        gbc.gridx = 0;
        inputForm.add(new JLabel("Monthly saving amount:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        inputForm.add(monthlySavingAmountInput, gbc);

        // Row 2: Glue to push Submit to bottom
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        inputForm.add(Box.createVerticalGlue(), gbc);

        // Row 3: Submit Button
        gbc.gridy++;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputForm.add(submitButton, gbc);

        mainWindow.taskPanel.add(inputForm);
    }
}
