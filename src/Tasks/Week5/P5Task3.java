package Tasks.Week5;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;

import javax.swing.*;

public class P5Task3 extends TaskInstance {
    private static final double INITIAL_TUITION = 10000.0;
    private static final double TUITION_INCREASE_RATE = 0.06;

    public P5Task3(MainWindow window) {
        super(window);

        this.name = "Task 3 - Financial Application: Compute Future Tuition";
        this.description = "Suppose that the tuition for a university is $10,000 this year and increases 6% every year. In one year, the tuition will be $10,600. Write a program that computes the tuition in ten years and the total cost of four years' worth of tuition after the tenth year.";
    }

    private static void onSubmit() {
        double totalCostOfFourYearsAfterTenYears = 0;

        double[] tuitionFeesByYear = new double[10];

        // Calculate the tuition in 10 years
        for (int i = 1; i <= 14; i++) {
            double currentTuition = INITIAL_TUITION + (INITIAL_TUITION * (TUITION_INCREASE_RATE) * i);

            if (i <= 10) {
                tuitionFeesByYear[i - 1] = currentTuition;
            } else {
                totalCostOfFourYearsAfterTenYears += currentTuition;
            }
        }

        // Show the result in a dialog box
        StringBuilder message = new StringBuilder();

        message.append("Tuition fees for the next 10 years:\n");
        for (int i = 1; i <= 10; i++) {
            message.append("Year ").append(i).append(": $").append(String.format("%.2f", tuitionFeesByYear[i - 1])).append("\n");
        }
        message.append("\nTotal cost of four years' worth of tuition after the tenth year: $").append(String.format("%.2f", totalCostOfFourYearsAfterTenYears));

        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    protected void prepareForm() {
        JButton showResultButton = new JButton("Show Result");
        showResultButton.addActionListener(e -> onSubmit());

        FormItem[] formItems = new FormItem[]{
                new FormItem("Click to show result:", showResultButton),
        };

        Form inputForm = new Form(formItems);
        this.mainWindow.insertForm(inputForm);
    }
}
