package Tasks.Week6;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;

import javax.swing.*;
import java.awt.*;

class ResultFrame extends JFrame {
    private static final String[] COLUMN_NAMES_KG_TO_POUNDS = {"<html><b>Kilograms</b></html>", "<html><b>Pounds</b></html>"};
    private static final String[] COLUMN_NAMES_POUNDS_TO_KG = {"<html><b>Pounds</b></html>", "<html><b>Kilograms</b></html>"};

    public ResultFrame(double[][] kilogramsToPounds, double[][] poundsToKilograms) {
        // Inherit from parent class and set title
        super("Week 6 Task 3 - Result");

        // basic properties of the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 500));
        setMinimumSize(new Dimension(500, 500));

        // Container (with padding) for the elements
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        String[][] kgToPoundsStringArr = new String[kilogramsToPounds.length][2];
        String[][] poundsToKgStringArr = new String[poundsToKilograms.length][2];

        // Construct the data set for the kilograms to pounds table
        for (int i = 0; i < kilogramsToPounds.length; i++) {
            kgToPoundsStringArr[i][0] = String.valueOf(kilogramsToPounds[i][0]); // Kilograms
            kgToPoundsStringArr[i][1] = String.format("%.1f", kilogramsToPounds[i][1]); // Pounds
        }

        // Construct the data set for the pounds to kilograms table
        for (int i = 0; i < poundsToKilograms.length; i++) {
            poundsToKgStringArr[i][0] = String.valueOf(poundsToKilograms[i][0]); // Pounds
            poundsToKgStringArr[i][1] = String.format("%.2f", poundsToKilograms[i][1]); // Kilograms
        }

        // Initialize the data table
        JTable kgToPoundsTable = new JTable(kgToPoundsStringArr, COLUMN_NAMES_KG_TO_POUNDS);
        JTable poundsToKgTable = new JTable(poundsToKgStringArr, COLUMN_NAMES_POUNDS_TO_KG);

        kgToPoundsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        kgToPoundsTable.setAlignmentX(Component.LEFT_ALIGNMENT);
        kgToPoundsTable.setMaximumSize(new Dimension(Integer.MAX_VALUE, kgToPoundsTable.getPreferredSize().height));
        poundsToKgTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        poundsToKgTable.setAlignmentX(Component.LEFT_ALIGNMENT);
        poundsToKgTable.setMaximumSize(new Dimension(Integer.MAX_VALUE, poundsToKgTable.getPreferredSize().height));

        panel.add(new JScrollPane(kgToPoundsTable));
        panel.add(Box.createRigidArea(new Dimension(0, 16)));
        panel.add(new JScrollPane(poundsToKgTable));

        // Bind the panel into the frame and display the window
        setContentPane(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

public class P6Task3 extends TaskInstance {
    private static final double POUND_TO_KILOGRAM_CONVERSION_FACTOR = 0.453;
    private static final double KILOGRAM_TO_POUND_CONVERSION_FACTOR = 2.204;

    public P6Task3(MainWindow window) {
        super(window);

        this.name = "Task 3 - Conversions between pounds and kilograms";
        this.description = "Write methods to convert between pounds and kilograms:\n" +
                "<ul>" +
                "<li><code>public static double poundToKilogram(double pound)</code></li>" +
                "<li><code>public static double kilogramToPound(double kilogram)</code></li>" +
                "</ul>" +
                "The formulas are:<br>" +
                "<code>pound = 0.453 * kilogram</code><br>" +
                "<code>kilogram = 2.204 * pound</code><br>" +
                "Display a table of conversions using these methods.";
    }

    private static double poundToKilogram(double pound) {
        return pound * POUND_TO_KILOGRAM_CONVERSION_FACTOR;
    }

    private static double kilogramToPound(double kilogram) {
        return kilogram * KILOGRAM_TO_POUND_CONVERSION_FACTOR;
    }

    private void makeConversions() {
        // From 1 to 199, step by 2
        double[][] kilogramsToPounds = new double[100][2];

        // From 20 to 515, step by 5
        double[][] poundsToKilograms = new double[100][2];

        for (int i = 0; i < 100; i++) {
            kilogramsToPounds[i][0] = i * 2 + 1; // Kilograms
            kilogramsToPounds[i][1] = kilogramToPound(kilogramsToPounds[i][0]); // Pounds

            poundsToKilograms[i][0] = i * 5 + 20; // Pounds
            poundsToKilograms[i][1] = poundToKilogram(poundsToKilograms[i][0]); // Kilograms
        }

        new ResultFrame(kilogramsToPounds, poundsToKilograms);
    }

    @Override
    protected void prepareForm() {
        JButton showConversionsButton = new JButton("Show Conversions");
        showConversionsButton.addActionListener(e -> makeConversions());

        FormItem[] formItems = new FormItem[]{
                new FormItem("Click to show conversions:", showConversionsButton),
        };

        Form inputForm = new Form(formItems);
        mainWindow.insertForm(inputForm);
    }
}
