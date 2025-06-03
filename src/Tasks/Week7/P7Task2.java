package Tasks.Week7;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P7Task2 extends TaskInstance {
    TextInput matrixAInput;
    TextInput matrixBInput;

    public P7Task2(MainWindow window) {
        super(window);

        this.name = "Task 2 - Algebra: Add Two Matrices";
        this.description = "Write a method to add two matrices. The header of the method is as follows:\n" +
                "<code>public static double[][] addMatrix(double[][] a, double[][] b)</code>\n" +
                "In order to be added, the two matrices must have the same dimensions and the same or compatible types of elements. Write a test program that prompts the user to enter two 2x2 matrices and displays their sum.";
    }

    private static double[][] addMatrix(double[][] a, double[][] b) {
        double[][] result = new double[2][2];

        result[0][0] = a[0][0] + b[0][0];
        result[0][1] = a[0][1] + b[0][1];
        result[1][0] = a[1][0] + b[1][0];
        result[1][1] = a[1][1] + b[1][1];

        return result;
    }

    private void onSubmit() {
        try {
            String[] matrixAValues = matrixAInput.getText().split(" ");
            String[] matrixBValues = matrixBInput.getText().split(" ");

            if (matrixAValues.length != 4 || matrixBValues.length != 4) {
                throw new IllegalArgumentException("Each matrix must contain exactly 4 elements.");
            }

            double[][] matrixA = {
                    {Double.parseDouble(matrixAValues[0]), Double.parseDouble(matrixAValues[1])},
                    {Double.parseDouble(matrixAValues[2]), Double.parseDouble(matrixAValues[3])}
            };

            double[][] matrixB = {
                    {Double.parseDouble(matrixBValues[0]), Double.parseDouble(matrixBValues[1])},
                    {Double.parseDouble(matrixBValues[2]), Double.parseDouble(matrixBValues[3])}
            };

            double[][] result = addMatrix(matrixA, matrixB);

            JOptionPane.showMessageDialog(null, "See the result in the console.");

            System.out.println("The matrices are added as follows:");
            System.out.printf(
                    " %.1f %.1f     %.1f %.1f     %.1f %.1f" +
                            "\n %.1f %.1f  +  %.1f %.1f  =  %.1f %.1f%n",
                    matrixA[0][0], matrixA[0][1], matrixB[0][0], matrixB[0][1], result[0][0], result[0][1],
                    matrixA[1][0], matrixA[1][1], matrixB[1][0], matrixB[1][1], result[1][0], result[1][1]
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input matrices are invalid.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        matrixAInput = new TextInput();
        matrixBInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Enter the first 2x2 matrix (space-separated values):", matrixAInput),
                new FormItem("Enter the second 2x2 matrix (space-separated values):", matrixBInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
