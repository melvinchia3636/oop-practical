package Tasks.Week4;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;
import java.util.HashMap;

public class P4Task5Amended extends TaskInstance {
    private HashMap<Character, String> CHARACTER_MAPPING;

    TextInput numberSequenceInput;

    public P4Task5Amended(MainWindow window) {
        super(window);

        initHashMap();

        this.name = "Task 5 (Amended) - Phone Key Pads";
        this.description = "The international standard letter/number mapping is found on the old telephone keypads. Write a program to prompt user for a sequence of numbers, and display the text that will come out on the screen when these numbers are being pressed on the keypad sequentially. Note that that digit \"0\" may be included in the number sequence to represent spaces. You may assume that there are not digits in the resulted string.";
    }

    private void initHashMap() {
        this.CHARACTER_MAPPING = new HashMap<>();
        this.CHARACTER_MAPPING.put('2', "abc");
        this.CHARACTER_MAPPING.put('3', "def");
        this.CHARACTER_MAPPING.put('4', "ghi");
        this.CHARACTER_MAPPING.put('5', "jkl");
        this.CHARACTER_MAPPING.put('6', "mno");
        this.CHARACTER_MAPPING.put('7', "pqrs");
        this.CHARACTER_MAPPING.put('8', "tuv");
        this.CHARACTER_MAPPING.put('9', "wxyz");
    }

    private void onSubmit() {
        char[] numberSequence = numberSequenceInput.getText().toCharArray();

        for (char c : numberSequence) {
            if (!CHARACTER_MAPPING.containsKey(c) && c != '0') {
                JOptionPane.showMessageDialog(null, "All numbers must be digits!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        StringBuilder result = new StringBuilder();


        for (int i = 0; i < numberSequence.length; i++) {
            if (numberSequence[i] == '0') {
                result.append(' ');
                continue;
            }

            String target = CHARACTER_MAPPING.get(numberSequence[i]);
            char currentChar = numberSequence[i];

            int idx = 0;
            i++;

            while (idx < target.length() - 1 && i < numberSequence.length && currentChar == numberSequence[i]) {
                i++;
                idx++;
            }

            i--;

            result.append(target.charAt(idx));
        }

        JOptionPane.showMessageDialog(null, "The result is: " + result);
    }

    @Override
    protected void prepareForm() {
        numberSequenceInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Sequence of numbers:", numberSequenceInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
