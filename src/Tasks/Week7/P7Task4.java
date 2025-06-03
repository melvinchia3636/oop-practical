package Tasks.Week7;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P7Task4 extends TaskInstance {
    TextInput row1Input;
    TextInput row2Input;
    TextInput row3Input;
    TextInput row4Input;

    public P7Task4(MainWindow window) {
        super(window);

        this.name = "Task 4 - Column Sorting";
        this.description = "(Column sorting) Implement the following method to sort the columns in a two dimensional array. A new array is returned and the original array is intact.\n" +
                "<code>public static double[][] sortColumns(double[][] m)</code>\n" +
                "Write a test program that prompts the user to enter a 4x4 matrix of double values and displays a new column-sorted matrix.";
    }

    private double[][] getDoubles() {
        String[] row1Values = row1Input.getText().split(" ");
        String[] row2Values = row2Input.getText().split(" ");
        String[] row3Values = row3Input.getText().split(" ");
        String[] row4Values = row4Input.getText().split(" ");

        if (row1Values.length != 4 || row2Values.length != 4 || row3Values.length != 4 || row4Values.length != 4) {
            throw new IllegalArgumentException("Each row must contain exactly 4 numbers.");
        }

        return new double[][]{
                {Double.parseDouble(row1Values[0]), Double.parseDouble(row1Values[1]), Double.parseDouble(row1Values[2]), Double.parseDouble(row1Values[3])},
                {Double.parseDouble(row2Values[0]), Double.parseDouble(row2Values[1]), Double.parseDouble(row2Values[2]), Double.parseDouble(row2Values[3])},
                {Double.parseDouble(row3Values[0]), Double.parseDouble(row3Values[1]), Double.parseDouble(row3Values[2]), Double.parseDouble(row3Values[3])},
                {Double.parseDouble(row4Values[0]), Double.parseDouble(row4Values[1]), Double.parseDouble(row4Values[2]), Double.parseDouble(row4Values[3])}
        };
    }

    private static double[][] sortColumns(double[][] matrix) {
        double[][] sortedMatrix = new double[4][4];

        for (int col = 0; col < 4; col++) {
            double[] column = new double[4];
            for (int row = 0; row < 4; row++) {
                column[row] = matrix[row][col];
            }
            java.util.Arrays.sort(column);
            for (int row = 0; row < 4; row++) {
                sortedMatrix[row][col] = column[row];
            }
        }

        return sortedMatrix;
    }

    private void onSubmit() {
        try {
            double[][] matrix = getDoubles();
            double[][] sortedMatrix = sortColumns(matrix);

            JOptionPane.showMessageDialog(null, "See the result in the console.");

            System.out.println("The sorted matrix is:");
            for (double[] row : sortedMatrix) {
                for (double value : row) {
                    System.out.printf("%.1f\t ", value);
                }
                System.out.println();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    @Override
    protected void prepareForm() {
        row1Input = new TextInput();
        row2Input = new TextInput();
        row3Input = new TextInput();
        row4Input = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Enter row 1 (4 numbers separated by spaces):", row1Input),
                new FormItem("Enter row 2 (4 numbers separated by spaces):", row2Input),
                new FormItem("Enter row 3 (4 numbers separated by spaces):", row3Input),
                new FormItem("Enter row 4 (4 numbers separated by spaces):", row4Input),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
