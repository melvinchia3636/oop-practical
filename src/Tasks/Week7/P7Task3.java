package Tasks.Week7;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P7Task3 extends TaskInstance {
    TextInput numbersInput;

    public P7Task3(MainWindow window) {
        super(window);

        this.name = "Task 3 - Find the Largest Element";
        this.description = "Write a method that finds the largest element in an array of double values using the following header:\n" +
                "<code>public static double max(double[] array)</code>\n" +
                "Write a test program that prompts the user to enter ten numbers, invokes this method to return the maximum value, and displays the maximum value.";
    }

    private static double max(double[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be empty.");
        }

        double max = array[0];
        for (double num : array) {
            if (num > max) {
                max = num;
            }
        }

        return max;
    }

    private void onSubmit() {
        String inputText = numbersInput.getText();
        String[] numberStrings = inputText.trim().split("\\s+");

        if (numberStrings.length != 10) {
            JOptionPane.showMessageDialog(null, "Please enter exactly ten numbers separated by spaces.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double[] numbers = new double[10];
        try {
            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Double.parseDouble(numberStrings[i]);
            }

            double maxNumber = max(numbers);
            JOptionPane.showMessageDialog(null, "The largest number is: " + maxNumber);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        numbersInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Enter ten numbers separated by spaces:", numbersInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
