package Tasks.Week5;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;

import javax.swing.*;
import java.util.Random;

public class P5Task7 extends TaskInstance {
    public P5Task7(MainWindow window) {
        super(window);

        this.name = "Task 7 - Game: Scissor, Rock, Paper";
        this.description = "<p>Write a program that plays the popular scissor-rock-paper game. (A scissor can cut a paper, a rock can knock a scissor, and a paper can wrap a rock.) The program randomly generates a number 0,1 , or 2 representing scissor, rock, and paper. The program prompts the user to enter a number 0,1 , or 2 and displays a message indicating whether the user or the computer wins, loses, or draws.</p><p>Revise the program to let the user continuously play until either the user or the computer wins three times more than their opponent.</p>";
    }

    private void startGame() {
        int userWins = 0;
        int computerWins = 0;

        Random random = new Random();

        while (userWins < 3 && computerWins < 3) {
            String input = JOptionPane.showInputDialog("Enter your choice (0: Scissor, 1: Rock, 2: Paper):");
            if (input == null) {
                return;
            }

            int userChoice;
            try {
                userChoice = Integer.parseInt(input);
                if (userChoice < 0 || userChoice > 2) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter 0, 1, or 2.", "Input Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            int computerChoice = random.nextInt(3);
            String[] choices = {"Scissor", "Rock", "Paper"};
            String resultMessage;

            if (userChoice == computerChoice) {
                resultMessage = "It's a draw! Both chose " + choices[userChoice] + ".";
            } else if ((userChoice == 0 && computerChoice == 2) ||
                       (userChoice == 1 && computerChoice == 0) ||
                       (userChoice == 2 && computerChoice == 1)) {
                userWins++;
                resultMessage = "You win! You chose " + choices[userChoice] + " and the computer chose " + choices[computerChoice] + ".";
            } else {
                computerWins++;
                resultMessage = "You lose! You chose " + choices[userChoice] + " and the computer chose " + choices[computerChoice] + ".";
            }

            JOptionPane.showMessageDialog(null, resultMessage + "\nCurrent Score - You: " + userWins + ", Computer: " + computerWins);
        }

        String finalMessage = userWins > computerWins ? "Congratulations! You won the game!" : "The computer won the game. Better luck next time!";
        JOptionPane.showMessageDialog(null, finalMessage);
    }

    @Override
    protected void prepareForm() {
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> startGame());

        FormItem[] formItems = new FormItem[]{
                new FormItem("Press to start the game: ", startButton)
        };

        Form inputForm = new Form(formItems);
        this.mainWindow.insertForm(inputForm);
    }
}
