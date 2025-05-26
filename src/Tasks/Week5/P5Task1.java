package Tasks.Week5;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import org.w3c.dom.ranges.RangeException;

import javax.swing.*;

public class P5Task1 extends TaskInstance {
    public P5Task1(MainWindow window) {
        super(window);

        this.name = "Task 1 - Pass or Fail";
        this.description = "<p>Write a program that prompts a student to enter a Java score. If the score is greater or equal to 60, display \"you pass the exam\"; otherwise, display \"you don't pass the exam\". Your program ends with input - 1. Here is a sample run:</p>";
    }

    private static void onStart() {
        int score = 0;

        do {
            String input = JOptionPane.showInputDialog("Enter a score (-1 to exit):");

            try {
                score = Integer.parseInt(input);

                if (score < 0 || score > 100) {
                    throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Score must be between 0 and 100.");
                }

                if (score >= 60) {
                    JOptionPane.showMessageDialog(null, "You pass the exam.");
                } else {
                    JOptionPane.showMessageDialog(null, "You don't pass the exam.");
                }
            } catch (Exception e) {
                if (e instanceof RangeException) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 0 and 100.", "Range Error", JOptionPane.ERROR_MESSAGE);
                }
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (score != -1);
    }

    @Override
    protected void prepareForm() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> onStart());

        FormItem[] formItems = new FormItem[]{
                new FormItem("Press the button to start: ", startButton)
        };

        Form inputForm = new Form(formItems);
        this.mainWindow.insertForm(inputForm);
    }
}
