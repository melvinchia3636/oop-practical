package Tasks.Week5;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;

import javax.swing.*;
import java.awt.*;

class ResultFrame extends JFrame {
    private static final String[] COLUMN_NAMES = {"<html><b>Celsius</b></html>", "<html><b>Fahrenheit</b></html>"};

    public ResultFrame() {
        // Inherit from parent class and set title
        super("Week 5 Task 2 - Result");

        // basic properties of the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 250));
        setMinimumSize(new Dimension(500, 250));

        // Container (with padding) for the elements
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // Construct the data set for the table
        String[][] data = new String[100][COLUMN_NAMES.length];
        for (int c = 0; c <= 100; c += 2) {
            data[c][0] = "" + c;
            data[c][1] = "" + (c * 9 / 5.0 + 32);
        }

        // Initialize the data table
        JTable table = new JTable(data, COLUMN_NAMES);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setAlignmentX(Component.LEFT_ALIGNMENT);
        table.setMaximumSize(new Dimension(Integer.MAX_VALUE, table.getPreferredSize().height));

        panel.add(new JScrollPane(table));

        // Bind the panel into the frame and display the window
        setContentPane(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

public class P5Task2 extends TaskInstance {
    public P5Task2(MainWindow window) {
        super(window);

        this.name = "Task 2 - Conversion from C to F";
        this.description = "Write a program that displays the table converting temperature from 0 to 100 celsius, stepping two degree each time (note that farenheit = celsius * 9 / 5 + 32):";
    }

    @Override
    protected void prepareForm() {
        JButton showTableButton = new JButton("Show Table");
        showTableButton.addActionListener(e -> {
            // Create a new ResultFrame to display the table
            new ResultFrame();
        });

        FormItem[] formItems = new FormItem[]{
                new FormItem("Press to show the result table:", showTableButton)
        };

        Form inputForm = new Form(formItems);
        this.mainWindow.insertForm(inputForm);
    }
}
