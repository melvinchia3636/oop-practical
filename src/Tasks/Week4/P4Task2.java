package Tasks.Week4;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;
import java.util.Random;

class RandomCharResult {
    int randInt1, randInt2, concatenatedInt;
    char resultChar;
}

public class P4Task2 extends TaskInstance {
    TextInput characterInput;

    public P4Task2(MainWindow window) {
        super(window);

        this.name = "Task 2 - Find the ASCII Code of a Character";
        this.description = "<p>Write a program that receives a character and displays its ASCII code (an integer between 0 and 127).</p><p><i>*After getting the output, use random technique to generate two random numbers from 0 to 9 and combine these two digits and display it as character. For example: Random number 1: 5, random number 2: 7, combined digits: 57, display as '9' (based on ASCII table)</i></p>";
    }

    private static RandomCharResult getRandomChar() {
        Random rand = new Random();
        RandomCharResult result = new RandomCharResult();

        result.randInt1 = rand.nextInt(10);
        result.randInt2 = rand.nextInt(10);

        String concatenatedInt = "" + result.randInt1 + result.randInt2;
        result.concatenatedInt = Integer.parseInt(concatenatedInt);

        result.resultChar = (char) result.concatenatedInt;

        return result;
    }

    private void onSubmit() {
        String text = characterInput.getText();
        if (text.length() != 1) {
            JOptionPane.showMessageDialog(null, "Please enter only one character", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int asciiCode = text.charAt(0);
        RandomCharResult randomChar = getRandomChar();

        JOptionPane.showMessageDialog(null, "The ASCII code for the character "  + text + " is " + asciiCode);
        JOptionPane.showMessageDialog(null, "Random number 1 is " + randomChar.randInt1 + ", Random number 2 is " + randomChar.randInt2 + ", combined digits is " + randomChar.concatenatedInt + " and it is display as \"" + randomChar.resultChar + "\"");
    }

    @Override
    protected void prepareForm() {
        characterInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Enter a character:", characterInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
