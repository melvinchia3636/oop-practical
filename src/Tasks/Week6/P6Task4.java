package Tasks.Week6;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P6Task4 extends TaskInstance {
    TextInput passwordInput;

    public P6Task4(MainWindow window) {
        super(window);

        this.name = "Task 4 - Check Password";
        this.description = "Some websites impose certain rules for passwords. Write a method that checks whether a string is a valid password. Suppose the password rules are as follows:\n<ul>" +
                "<li>A password must have at least ten characters.</li>\n" +
                "<li>A password consists of only letters and digits.</li>\n" +
                "<li>A password must contain at least three digits.</li>\n" +
                "</ul>\n" +
                "Write a program that prompts the user to enter a password and displays <b>Valid Password</b> if the rules are followed or <b>Invalid Password</b> otherwise.";
    }

    private static boolean isValidPassword(String password) {
        if (password.length() < 10) {
            return false;
        }

        int digitCount = 0;
        for (char c : password.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false; // Contains invalid characters
            }
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        return digitCount >= 3; // Must contain at least three digits
    }

    private void onSubmit() {
        String password = passwordInput.getText();

        if (isValidPassword(password)) {
            JOptionPane.showMessageDialog(null, "Valid Password");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Password", "Password Check", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        passwordInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Enter a password:", passwordInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
