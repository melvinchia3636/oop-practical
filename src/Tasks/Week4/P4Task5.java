package Tasks.Week4;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;
import java.util.HashMap;

public class P4Task5 extends TaskInstance {
    private HashMap<String, Integer> CHAR_MAP;

    TextInput charInput;

    public P4Task5(MainWindow window) {
        super(window);

        initCharMap();

        this.name = "Task 5 - Phone Key Pads";
        this.description = "The international standard letter/number mapping is found on the old telephone keypads. Write a program to prompt user for a letter. Output the number on the keypad that corresponds to that letter.";
    }

    private void initCharMap() {
        CHAR_MAP = new HashMap<>();
        CHAR_MAP.put("abc", 2);
        CHAR_MAP.put("def", 3);
        CHAR_MAP.put("ghi", 4);
        CHAR_MAP.put("jkl", 5);
        CHAR_MAP.put("mno", 6);
        CHAR_MAP.put("pqrs", 7);
        CHAR_MAP.put("tuv", 8);
        CHAR_MAP.put("wxyz", 9);
    }

    private int getCorrespondingNumber(char letter) {
        int result = 0;
        letter = Character.toLowerCase(letter);

        for (String key : CHAR_MAP.keySet()) {
            if (key.indexOf(letter) != -1) {
                result = CHAR_MAP.get(key);
            }
        }

        return result;
    }

    private void onSubmit() {
        String response = charInput.getText();

        if (response.length() != 1) {
            JOptionPane.showMessageDialog(null, "Input should be exactly one character", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        char letter = response.charAt(0);

        if (!Character.isLetter(letter)) {
            JOptionPane.showMessageDialog(null, "Input should be a letter", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = getCorrespondingNumber(letter);

        JOptionPane.showMessageDialog(null, "The number corresponded to the letter \"" + letter + "\" is: " + result);
    }

    @Override
    protected void prepareForm() {
        charInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Enter a letter (lowercase):", charInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
