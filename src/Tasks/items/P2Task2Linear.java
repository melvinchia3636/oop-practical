package Tasks.items;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.SubmitButton;
import UI.components.TextInput;

import javax.swing.*;
import java.awt.*;

public class P2Task2Linear extends TaskInstance {
    private TextInput numberInput;

    public P2Task2Linear(MainWindow window) {
        super(window);

        name = "Task 2 - Multiply the Digits in an Integer";
        description = "<p>Write a program that reads an integer between 0 and 1000 and multiplies all the digits in the integer. For example, if an integer is 932, the multiplication of all its digits is 54.</p><p><i>Hint:</i> Use the % operator to extract digits, and use the / operator to remove the extracted digit. For instance, 932 % 10 = 2 and 932 / 10 = 93.</p><p><i>For now, just focus on 3 digits.</i></p>";
    }

    // Main logic for the task
    public static int multiplyDigit(int number) {
        // Decomposition: breaking the number apart
        int unitDigit = number % 10;
        number /= 10;
        int tensDigit = number % 10;
        number /= 10; // number is now the digit in the hundreds place

        return number * unitDigit * tensDigit;
    }

    private void onSubmit() {
        try {
            // Parse the inputs, do the Meth, and show the result in a dialog box
            int number = Integer.parseInt(numberInput.getText());
            if (number < 100 || number > 999) { // Check if the input is in range. If not, yeet an error
                throw new NumberFormatException();
            }

            int result = multiplyDigit(number);

            JOptionPane.showMessageDialog(null, "The result of multiplying all digits in the number " + number + " is " + result);
        } catch (NumberFormatException e) {
            // Show error when the user input is invalid (aka not a proper integer or not in range)
            JOptionPane.showMessageDialog(null, "Please enter a valid three digit number from 100 to 999.", "Number Parsing Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        JPanel inputForm = new JPanel();
        inputForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        numberInput = new TextInput();
        SubmitButton submitButton = new SubmitButton(e -> onSubmit());


        // Gap between each component
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.gridy = 0;

        // Row 1: Number Input
        gbc.gridx = 0;
        inputForm.add(new JLabel("A three digit number:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        inputForm.add(numberInput, gbc);

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
