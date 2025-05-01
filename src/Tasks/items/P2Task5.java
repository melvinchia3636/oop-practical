package Tasks.items;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.SubmitButton;
import UI.components.TextInput;

import javax.swing.*;
import java.awt.*;
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
        JPanel inputForm = new JPanel();
        inputForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        heightInput = new TextInput();
        weightInput = new TextInput();
        SubmitButton submitButton = new SubmitButton(e -> onSubmit());


        // Gap between each component
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.gridy = 0;

        // Row 1: Height (in inches)
        gbc.gridx = 0;
        inputForm.add(new JLabel("Height (in inches):"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        inputForm.add(heightInput, gbc);

        // Row 2: Weight (in pounds)
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 0;
        inputForm.add(new JLabel("Weight (in pounds):"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        inputForm.add(weightInput, gbc);

        // Row 3: Glue to push Submit to bottom
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        inputForm.add(Box.createVerticalGlue(), gbc);

        // Row 4: Submit Button
        gbc.gridy++;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputForm.add(submitButton, gbc);

        mainWindow.taskPanel.add(inputForm);
    }
}
