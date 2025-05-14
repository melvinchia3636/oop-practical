package Tasks.Week4;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

public class P4Task4 extends TaskInstance {
    private TextInput letterInput;

    public P4Task4(MainWindow window) {
        super(window);

        this.name = "Task 4 - Vowel or Consonant?";
        this.description = "<p>Write a program that prompts the user to enter a letter and check whether the letter is a vowel or consonant. For a nonletter input, display invalid input.</p>";
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(Character.toLowerCase(c)) >= 0;
    }

    private void onSubmit() {
        String text = letterInput.getText();

        if (text.length() != 1) {
            JOptionPane.showMessageDialog(null, "Please enter exactly one letter", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        char letter = text.charAt(0);

        if (!Character.isLetter(letter)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid letter", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean charIsVowel = isVowel(letter);
        JOptionPane.showMessageDialog(null, "The letter " + letter + " is a " + (charIsVowel ? "vowel" : "consonant"));
    }

    @Override
    protected void prepareForm() {
        letterInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Letter from A-Z (non case-sensitive):", letterInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
