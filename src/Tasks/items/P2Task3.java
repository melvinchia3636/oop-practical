package Tasks.items;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

class TaskResult {
    int year;
    int month;
}

public class P2Task3 extends TaskInstance {
    private final static int AMOUNT_OF_MINUTES_IN_A_DAY = 1440; // 60 minutes * 24 hours
    private final static int AMOUNT_OF_MINUTES_IN_A_YEAR = 525600; // 60 minutes * 24 hours * 365 days

    private TextInput minutesInput;

    public P2Task3(MainWindow window) {
        super(window);

        name = "Task 3 - Find the Number of Years";
        description = "<p>Write a program that prompts the user to enter the minutes (e.g., 1 billion), and displays the number of years and remaining days for the minutes. For simplicity, assume that a year has 365 days. Here is a sample run:</p>";
    }

    // Main logic for the task
    private static TaskResult getYearsAndDaysFromMinutes(int minutes) {
        int amountOfYears = minutes / AMOUNT_OF_MINUTES_IN_A_YEAR;
        int amountOfDays = (minutes % AMOUNT_OF_MINUTES_IN_A_YEAR) / AMOUNT_OF_MINUTES_IN_A_DAY;

        TaskResult taskResult = new TaskResult();
        taskResult.year = amountOfYears;
        taskResult.month = amountOfDays;

        return taskResult;
    }

    private void onSubmit() {
        try {
            // Parse the inputs, do the Meth, and show the result in a dialog box
            int minutes = Integer.parseInt(minutesInput.getText());
            TaskResult result = getYearsAndDaysFromMinutes(minutes);

            JOptionPane.showMessageDialog(null, minutes + " minutes is approximately " + result.year + " years and " + result.month + " months");
        } catch (NumberFormatException e) {
            // Show error when the user input is invalid (aka not a proper integer)
            JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Number Parsing Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        minutesInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Minutes:", minutesInput),
        };
        Form inputForm = new Form(formItems, e -> onSubmit());

        mainWindow.insertForm(inputForm);
    }
}
