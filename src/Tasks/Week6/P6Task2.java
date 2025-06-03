package Tasks.Week6;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P6Task2 extends TaskInstance {
    TextInput num1Input;
    TextInput num2Input;
    TextInput num3Input;

    public P6Task2(MainWindow window) {
        super(window);

        this.name = "Task 2 - Sort three numbers";
        this.description = "Write a method with the following header to display three numbers in decreasing order:\n" +
                "<code>public static void displaySortedNumbers (double num1, double num2, double num3)</code>\n" +
                "Write a test program that prompts the user to enter three numbers and invokes the method to display them in decreasing order.";
    }

    private static double[] sortNumbersDescending(double num1, double num2, double num3) {
        double[] numbers = new double[3];

        // Most naive way to sort three numbers
        if (num1 >= num2 && num1 >= num3) {
            numbers[0] = num1;

            if (num2 >= num3) {
                numbers[1] = num2;
                numbers[2] = num3;
            } else {
                numbers[1] = num3;
                numbers[2] = num2;
            }
        } else if (num2 >= num1 && num2 >= num3) {
            numbers[0] = num2;

            if (num1 >= num3) {
                numbers[1] = num1;
                numbers[2] = num3;
            } else {
                numbers[1] = num3;
                numbers[2] = num1;
            }
        } else {
            numbers[0] = num3;

            if (num1 >= num2) {
                numbers[1] = num1;
                numbers[2] = num2;
            } else {
                numbers[1] = num2;
                numbers[2] = num1;
            }
        }

        return numbers;
    }

    private void onSubmit() {
        try {
            double num1 = Double.parseDouble(num1Input.getText());
            double num2 = Double.parseDouble(num2Input.getText());
            double num3 = Double.parseDouble(num3Input.getText());

            double[] sortedNumbers = sortNumbersDescending(num1, num2, num3);

            String message = String.format("The numbers in decreasing order are: %.2f, %.2f, %.2f", sortedNumbers[0], sortedNumbers[1], sortedNumbers[2]);
            JOptionPane.showMessageDialog(null, message);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        num1Input = new TextInput();
        num2Input = new TextInput();
        num3Input = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Number 1:", num1Input),
                new FormItem("Number 2:", num2Input),
                new FormItem("Number 3:", num3Input)
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
