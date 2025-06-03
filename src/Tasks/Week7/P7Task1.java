package Tasks.Week7;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;

class EventOddCount {
    public int evenCount;
    public int oddCount;
}

public class P7Task1 extends TaskInstance {
    TextInput numberInput;

    public P7Task1(MainWindow window) {
        super(window);

        this.name = "Task 1 - The Number of Even Numbers and Odd Numbers";
        this.description = "Write a program that reads ten integers, and then display the number of even numbers and odd numbers.";
    }

    private static EventOddCount countEvenOdd(int[] numbers) {
        EventOddCount count = new EventOddCount();

        for (int number : numbers) {
            if (number == 0) break; // Stop counting when we hit 0
            if (number % 2 == 0) {
                count.evenCount++;
            } else {
                count.oddCount++;
            }
        }

        return count;
    }

    private void onSubmit() {
        String inputText = numberInput.getText();
        String[] numbersStr = inputText.split("\\s+");

        int[] numbers = new int[numbersStr.length];

        if (numbersStr.length == 0) {
            JOptionPane.showMessageDialog(null, "Please enter at least one integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < numbersStr.length; i++) {
            try {
                numbers[i] = Integer.parseInt(numbersStr[i]);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input: " + numbersStr[i], "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        EventOddCount count = countEvenOdd(numbers);

        String message = String.format("Number of even numbers: %d\nNumber of odd numbers: %d", count.evenCount, count.oddCount);
        JOptionPane.showMessageDialog(null, message);
    }

    @Override
    protected void prepareForm() {
        numberInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Enter ten integers (end with 0):", numberInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());
        mainWindow.insertForm(inputForm);
    }
}
