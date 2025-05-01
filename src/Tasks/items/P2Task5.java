package Tasks.items;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;
import java.text.DecimalFormat;

public class P2Task5 extends TaskInstance {
    private final static double INCHES_TO_METERS_RATIO = 0.0254;
    private final static double POUNDS_TO_KG_RATIO = 0.45359237;

    TextInput heightInput;
    TextInput weightInput;

    public P2Task5(MainWindow window) {
        super(window);

        name = "Task 5 - Health Application: Computing BMI";
        description = "<p>Body Mass Index (BMI) is a measure of health on weight. It can be calculated by taking your weight in kilograms and dividing, by the square of your height in meters. Write a program that prompts the user to enter a weight in pounds and height in inches and displays the BMI. Note one pound is 0.45359237 kilograms and one inch is 0.0254 meters.</p>";
    }

    // Main logic of the task
    private static double calculateBMI(double height, double weight) {
        double heightInMeters = height * INCHES_TO_METERS_RATIO;
        double weightInKg = weight * POUNDS_TO_KG_RATIO;

        return weightInKg / (heightInMeters * heightInMeters);
    }

    private void onSubmit() {
        try {
            // Parse the inputs, do the Meth, and show the result in a dialog box
            double height = Double.parseDouble(heightInput.getText());
            double weight = Double.parseDouble(weightInput.getText());

            double result = calculateBMI(height, weight);

            JOptionPane.showMessageDialog(null, "BMI is: " + new DecimalFormat("#.####").format(result));
        } catch (NumberFormatException e) {
            // Show error when the user input is invalid (aka not a proper double)
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Number Parsing Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        heightInput = new TextInput();
        weightInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Height (in inches):", heightInput),
                new FormItem("Weight (in pounds):", weightInput)
        };
        Form inputForm = new Form(formItems, e -> onSubmit());

        mainWindow.insertForm(inputForm);
    }
}
