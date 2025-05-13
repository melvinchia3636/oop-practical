package Tasks.Week2;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P2Task2Linear extends TaskInstance {
    private TextInput numberInput;

    public P2Task2Linear(MainWindow window) {
        super(window);

        name = "Week 2 Task 2 - Multiply the Digits in an Integer";
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
        numberInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("A number between 0 and 1000: ", numberInput),
        };
        Form inputForm = new Form(formItems, e -> onSubmit());

        mainWindow.insertForm(inputForm);
    }
}
