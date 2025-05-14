package Tasks.Week4;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P4Task6 extends TaskInstance {
    private TextInput SSNInput;

    public P4Task6(MainWindow window) {
        super(window);

        this.name = "Task 6 - Check SSN";
        this.description = "<p>Write a program that prompts the user to enter a Social Security number in the format DDD-DD-DDDD, where D is a digit. Your program should check whether the input is valid.</p>";
    }

    private void onSubmit() {
        String SSN = SSNInput.getText();

        boolean isValidSSN = SSN.matches("^\\d{3}-\\d{2}-\\d{4}$");

        if (isValidSSN) {
            JOptionPane.showMessageDialog(null, "Congratulations! Your Social Security number is valid.");
        } else {
            JOptionPane.showMessageDialog(null, "Social Security number is invalid.", "Invalid Social Security Number", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        SSNInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Social Security Number (SSN): ", SSNInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
