package Tasks.Week3;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.ComboBoxRenderer;
import UI.components.Form;
import UI.components.FormItem;

import javax.swing.*;
import java.awt.*;

public class P3Task2 extends TaskInstance {
    private JComboBox<String> colorSelector;

    public P3Task2(MainWindow window) {
        super(window);

        this.name = "Task 2 - Determining the Next Color for a Stop Light";
        this.description = "<p>The normal behavior for a stop light is to cycle from Red to Green to Yellow to Red (and continues with this pattern). Write a java program TrafficLightChecker.java, which will determine the next color of a stop light in this pattern, Red to Green to Yellow to Red based on the current stop light provided by the user.</p>" +
                "<p>You must implement the following using a suitable if decision statement.</p>" +
                "<ol><li>Have the user enter the value for the currentColor.</li>" +
                "<li>Compute the next color stop light based on the currentColor.</li>" +
                "<li>Alert the user for any invalid value of color.</li></ol>";
    }

    private static String getNextColor(String color) {
        switch (color) {
            case "Red":
                return "Green";
            case "Green":
                return "Yellow";
            case "Yellow":
                return "Red";
            default:
                return null;
        }
    }

    private void onSubmit() {
        String color = (String) colorSelector.getSelectedItem();

        assert color != null;
        String nextColor = getNextColor(color);

        if (nextColor == null) {
            JOptionPane.showMessageDialog(null, "Seems like you have somehow selected an invalid color.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "The next color is " + nextColor + ".");
    }

    @Override
    protected void prepareForm() {
        String[] options = new String[]{"Red", "Yellow", "Green"};
        Color[] colors = new Color[]{Color.RED, Color.YELLOW, Color.GREEN};

        colorSelector = new JComboBox<>(options);

        colorSelector.setSelectedIndex(0);
        colorSelector.setBorder(BorderFactory.createCompoundBorder(
                colorSelector.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        ComboBoxRenderer renderer = new ComboBoxRenderer(colorSelector);
        colorSelector.setRenderer(renderer);

        renderer.setStrings(options);
        renderer.setColors(colors);

        FormItem[] formItems = new FormItem[]{
                new FormItem("Select color: ", colorSelector),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());

        mainWindow.insertForm(inputForm);
    }
}
