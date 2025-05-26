package Tasks.Week5;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

class Result {
    int vowelCount;
    int consonantCount;
}

public class P5Task5 extends TaskInstance {
    TextInput inputString;

    public P5Task5(MainWindow window) {
        super(window);

        this.name = "Task 5 - Count Vowels and Consonants";
        this.description = "Assume that the letters A, E, I, O, and U are vowels. Write a program that prompts the user to enter a string, and displays the number of vowels and consonants in the string.";
    }

    private Result calculateVowelsAndConsonants(String text) {
        Result result = new Result();
        result.vowelCount = 0;
        result.consonantCount = 0;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowerC = Character.toLowerCase(c);
                if ("aeiou".indexOf(lowerC) >= 0) {
                    result.vowelCount++;
                } else {
                    result.consonantCount++;
                }
            }
        }

        return result;
    }

    private void onSubmit() {
        String text = inputString.getText();

        Result result = calculateVowelsAndConsonants(text);

        String message = String.format("The string contains %d vowels and %d consonants.", result.vowelCount, result.consonantCount);
        JOptionPane.showMessageDialog(null, message, "Vowel and Consonant Count", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    protected void prepareForm() {
        inputString = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Enter a string:", inputString),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
