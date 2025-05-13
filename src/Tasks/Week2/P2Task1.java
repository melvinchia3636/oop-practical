package Tasks.Week2;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P2Task1 extends TaskInstance {
    private TextInput waterAmountInput;
    private TextInput initialTemperatureInput;
    private TextInput finalTemperatureInput;

    public P2Task1(MainWindow window) {
        super(window);

        name = "Week 2 Task 1 - Science: Calculating Energy";
        description = ("<p>Write a program that calculates the energy needed to heat water from an initial temperature to a final temperature. Your program should prompt the user to enter the amount of water in kilograms and the initial and final temperatures of the water. The formula to compute the energy is</p><code>Q = M * (finalTemperature - initialTemperature) * 4184</code><p>where M is the weight of water in kilograms, initial and final temperatures are in degrees Celsius, and energy Q is measured in joules.</p>");
    }

    // Main logic for the task
    private static double calculateEnergy(double amountOfWater, double initialTemperature, double finalTemperature) {
        return amountOfWater * (finalTemperature - initialTemperature) * 4184;
    }

    private void onSubmit() {
        try {
            // Parse the inputs, do the Meth, and show the result in a dialog box
            double waterAmount = Double.parseDouble(waterAmountInput.getText());
            double initialTemperature = Double.parseDouble(initialTemperatureInput.getText());
            double finalTemperature = Double.parseDouble(finalTemperatureInput.getText());

            double energy = calculateEnergy(waterAmount, initialTemperature, finalTemperature);

            JOptionPane.showMessageDialog(null, "The energy required to heat " + waterAmount + " kg of water from " + initialTemperature + " degree Celsius to " + finalTemperature + " degree Celsius is " + energy + " joules.");

        } catch (NumberFormatException e) {
            // Show error when the user input is invalid (aka not a proper double)
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Number Parsing Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        waterAmountInput = new TextInput();
        initialTemperatureInput = new TextInput();
        finalTemperatureInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Water amount (in kg): ", waterAmountInput),
                new FormItem("Initial temperature (in Celsius): ", initialTemperatureInput),
                new FormItem("Final temperature (in Celsius): ", finalTemperatureInput)
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
