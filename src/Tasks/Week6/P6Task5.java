package Tasks.Week6;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P6Task5 extends TaskInstance {
    TextInput stringInput;

    public P6Task5(MainWindow window) {
        super(window);

        this.name = "Task 5 - Count the Letters in a String";
        this.description = "Write a method that counts the number of letters in a string using the following header:\n" +
                "<p><code>public static int countLetters (String s)</code></p>\n" +
                "Write a test program that prompts the user to enter a string and displays the number of letters in the string.";
    }

    private static int countLetters(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                count++;
            }
        }

        return count;
    }

    private void onSubmit() {
        String inputText = stringInput.getText();
        int letterCount = countLetters(inputText);

        JOptionPane.showMessageDialog(null, "The number of letters in the string is: " + letterCount);
    }

    @Override
    protected void prepareForm() {
        stringInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Enter a string:", stringInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
