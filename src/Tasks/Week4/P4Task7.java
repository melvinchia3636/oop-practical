package Tasks.Week4;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;
import java.util.HashMap;

public class P4Task7 extends TaskInstance {
    private HashMap<Character, String> MAJOR_CODE;
    private HashMap<Integer, String> YEAR_OF_STUDY;

    private TextInput twoCharInput;

    public P4Task7(MainWindow window) {
        super(window);

        prepareData();

        this.name = "Task 7 - Student Major and Status";
        this.description = "<p>Write a program that prompts the user to enter two characters and displays the major and status represented in the characters. The first character indicates the major and the second is number character 1, 2, 3, 4, which indicates whether a student is a freshman, sophomore, junior, or senior. Suppose the following characters are used to denote the majors:</p><code>I</code>: Information Management<p><code>C</code>: Computer Science</p><p><code>A</code>: Accounting</p>";
    }

    private void prepareData() {
        MAJOR_CODE = new HashMap<>();
        YEAR_OF_STUDY = new HashMap<>();

        MAJOR_CODE.put('I', "Information Management");
        MAJOR_CODE.put('C', "Computer Science");
        MAJOR_CODE.put('A', "Accounting");

        YEAR_OF_STUDY.put(1, "Freshman");
        YEAR_OF_STUDY.put(2, "Sophomore");
        YEAR_OF_STUDY.put(3, "Junior");
        YEAR_OF_STUDY.put(4, "Senior");
    }

    private void onSubmit() {
        String userInput = twoCharInput.getText();

        if (userInput.length() != 2) {
            JOptionPane.showMessageDialog(null, "The input should be exactly two characters", "Input Format Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        char majorCode = userInput.charAt(0);
        int yearCode = Integer.parseInt(userInput.charAt(1) + "");

        if (!MAJOR_CODE.containsKey(majorCode)) {
            JOptionPane.showMessageDialog(null, "\"" + majorCode + "\" is not a valid major code", "Invalid Major", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!YEAR_OF_STUDY.containsKey(yearCode)) {
            JOptionPane.showMessageDialog(null, "\"" + yearCode + "\" is not a valid year", "Invalid Year", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String major = MAJOR_CODE.get(majorCode);
        String year = YEAR_OF_STUDY.get(yearCode);

        JOptionPane.showMessageDialog(null, major + " " + year);
    }

    @Override
    protected void prepareForm() {
        twoCharInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Two Characters:", twoCharInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
