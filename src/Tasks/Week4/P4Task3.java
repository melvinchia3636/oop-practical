package Tasks.Week4;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;
import java.util.Arrays;

public class P4Task3 extends TaskInstance {
    TextInput country1Input;
    TextInput country2Input;
    TextInput country3Input;

    public P4Task3(MainWindow window) {
        super(window);

        this.name = "Task 3 - Enter Three Countries";
        this.description = "Write a program that prompts the user to enter three countries and displays them in descending order.";
    }

    private void onSubmit() {
        String[] countries = {
                country1Input.getText(),
                country2Input.getText(),
                country3Input.getText()
        };

        Arrays.sort(countries);

        JOptionPane.showMessageDialog(null, "The three countries in descending order is: " + countries[2] + ", " + countries[1] + ", " + countries[0]);
    }

    @Override
    protected void prepareForm() {
        country1Input = new TextInput();
        country2Input = new TextInput();
        country3Input = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Country 1:", country1Input),
                new FormItem("Country 2:", country2Input),
                new FormItem("Country 3:", country3Input)
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
