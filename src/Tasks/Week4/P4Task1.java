package Tasks.Week4;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P4Task1 extends TaskInstance {
    private TextInput sideCountInput;
    private TextInput sideLengthInput;

    public P4Task1(MainWindow window) {
        super(window);

        this.name = "Task 1 - Geometry: Area of a Regular Polygon";
        this.description = "<p>A regular polygon is an $n$-sided polygon in which all sides are of the same length and all angles have the same degree (i.e., the polygon is both equilateral and equiangular). The formula for computing the area of a regular polygon is</p><center><code>Area = (n x s^2) / (4 x tan(pi/n))</code></center><p>Here, s is the length of a side. Write a program that prompts the user to enter the number of sides and their length of a regular polygon and displays its area.</p>";
    }

    private static double calculateArea(int sideCount, double sideLength) {
        return (sideCount * Math.pow(sideLength, 2)) / (4 * Math.tan(Math.PI / sideCount));
    }

    private void onSubmit() {
        try {
            int sideCount = Integer.parseInt(sideCountInput.getText());
            double sideLength = Double.parseDouble(sideLengthInput.getText());

            double area = calculateArea(sideCount, sideLength);

            JOptionPane.showMessageDialog(null, "The area of the polygon is " + area);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number. Side count must be an integer, and side length must be a double", "Number Parsing Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        sideCountInput = new TextInput();
        sideLengthInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Amount of sides:", sideCountInput),
                new FormItem("Length of each side:", sideLengthInput)
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
