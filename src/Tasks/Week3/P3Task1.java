package Tasks.Week3;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;
import org.w3c.dom.ranges.RangeException;

import javax.swing.*;

public class P3Task1 extends TaskInstance {
    private TextInput waveLengthInput;

    public P3Task1(MainWindow window) {
        super(window);

        name = "Task 1 - Spectrum WaveLength";
        description = "<p>You must implement the following using a suitable if decision statement.\n" +
                "1. Prompt the user to enter the wavelength, the wavelength should be of type\n" +
                "double.\n" +
                "2. For each range (e.g. 380-450), the number on the left is included in the range,\n" +
                "but the number on the right is not included in the range.\n" +
                "3. If the input value is not found on the visible spectrum, then state that the\n" +
                "wavelength is not within the visible spectrum.</p>";
    }

    private String getColorCode(double waveLength) {
        if (waveLength < 380 || waveLength > 750) {
            return null;
        }

        if (waveLength < 450) {
            return "Violet";
        } else if (waveLength < 495) {
            return "Blue";
        } else if (waveLength < 570) {
            return "Green";
        } else if (waveLength < 590) {
            return "Yellow";
        } else if (waveLength < 620) {
            return "Orange";
        } else {
            return "Red";
        }
    }

    private void onSubmit() {
        try {
            // Parse the inputs, do the Meth, and show the result in a dialog box
            double waveLength = Double.parseDouble(waveLengthInput.getText());

            String colorCode = getColorCode(waveLength);
            String tempLevel = "";

            if (colorCode == null) {
                throw new IllegalArgumentException("The entered wavelength is not a part of the visible spectrum");
            }

            if (colorCode.equals("Green")) {
                String tempString = JOptionPane.showInputDialog("Enter the temperature in Celsius:");
                    double temperature = Double.parseDouble(tempString);
                    tempLevel = temperature < 40 ? "Normal " : "High ";
            }

            JOptionPane.showMessageDialog(null, "The color is " + tempLevel + colorCode);
        } catch (NumberFormatException e) {
            // Show error when the user input is invalid (aka not a proper double)
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Number Parsing Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            // Show error when the user input is out of rang
            JOptionPane.showMessageDialog(null, e.getMessage(), "Out of Range Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        waveLengthInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Wave length (between 380 - 750)", waveLengthInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
