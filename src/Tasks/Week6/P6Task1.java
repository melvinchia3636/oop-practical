package Tasks.Week6;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P6Task1 extends TaskInstance {
    TextInput numberInput;

    public P6Task1(MainWindow window) {
        super(window);

        this.name = "Task 1 - Display an Integer Reversed";
        this.description = "Write a method with the following header to display an integer in reverse order:\n" +
                "<p><code>public static void reverse(int number)</code></p>\n" +
                "For example, <code>reverse(3456)</code> displays 6543. Write a test program that prompts the user to enter an integer then displays its reversal.";
    }

    private static int reverse(int number) {
        int reversed = 0;
        while (number > 0) {
            reversed = reversed * 10 + number % 10;
            number /= 10;
        }
        return reversed;
    }

    private void onSubmit() {
        String inputText = numberInput.getText();
        try {
            int number = Integer.parseInt(inputText);
            int reversed = reverse(number);

            JOptionPane.showMessageDialog(null, "The reversed integer is: " + reversed);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        numberInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Enter an integer:", numberInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
