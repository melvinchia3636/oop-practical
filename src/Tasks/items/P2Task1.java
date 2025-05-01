package Tasks.items;

import java.awt.*;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.SubmitButton;
import UI.components.TextInput;

import javax.swing.*;

public class P2Task1 extends TaskInstance {
    private TextInput waterAmountInput;
    private TextInput initialTemperatureInput;
    private TextInput finalTemperatureInput;

    public P2Task1(MainWindow window) {
        super(window);

        name = "Task 1 - Science: Calculating Energy";
        description = ("<p>Write a program that calculates the energy needed to heat water from an initial temperature to a final temperature. Your program should prompt the user to enter the amount of water in kilograms and the initial and final temperatures of the water. The formula to compute the energy is</p><code>Q = M * (finalTemperature - initialTemperature) * 4184</code><p>where M is the weight of water in kilograms, initial and final temperatures are in degrees Celsius, and energy Q is measured in joules.</p>");
    }

    // Main logic for the task
    public static double calculateEnergy(double amountOfWater, double initialTemperature, double finalTemperature) {
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
        JPanel inputForm = new JPanel();
        inputForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        waterAmountInput = new TextInput();
        initialTemperatureInput = new TextInput();
        finalTemperatureInput = new TextInput();
        SubmitButton submitButton = new SubmitButton(e -> onSubmit());

        // Gap between each component
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.gridy = 0;

        // Row 1: Water Amount
        gbc.gridx = 0;
        inputForm.add(new JLabel("Water Amount:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        inputForm.add(waterAmountInput, gbc);

        // Row 2: Initial Temperature
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 0;
        inputForm.add(new JLabel("Initial Temperature:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        inputForm.add(initialTemperatureInput, gbc);

        // Row 3: Final Temperature
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weightx = 0;
        inputForm.add(new JLabel("Final Temperature:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        inputForm.add(finalTemperatureInput, gbc);

        // Row 4: Glue to push Submit to bottom
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        inputForm.add(Box.createVerticalGlue(), gbc);

        // Row 5: Submit Button
        gbc.gridy++;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputForm.add(submitButton, gbc);

        mainWindow.taskPanel.add(inputForm);
    }
}
