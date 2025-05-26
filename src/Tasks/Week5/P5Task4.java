package Tasks.Week5;

import Tasks.TaskInstance;
import UI.MainWindow;
import UI.components.Form;
import UI.components.FormItem;
import UI.components.TextInput;

import javax.swing.*;
import java.util.Arrays;

class PromptResult {
    String[] names;
    double[] scores;
}

class LowestScores {
    String[] lowestNames;
    double lowestScore;
    String[] secondLowestNames;
    double secondLowestScore;
}

public class P5Task4 extends TaskInstance {
    TextInput numberOfStudentsInput;

    public P5Task4(MainWindow window) {
        super(window);

        this.name = "Task 4 - Find the Two Lowest Scores";
        this.description = "Write a program that prompts the user to enter the number of students and each student's name and score, and finally displays the names of the students with the lowest and second-lowest scores.";
    }

    private PromptResult getStudentData(int numberOfStudents) {
        PromptResult result = new PromptResult();
        result.names = new String[numberOfStudents];
        result.scores = new double[numberOfStudents];

        int currentIndex = 0;
        while (currentIndex < numberOfStudents) {
            String name = "";
            name = JOptionPane.showInputDialog("Enter the name of student " + (currentIndex + 1) + ":").trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty. Please enter a valid name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }


            double score = -1;
            try {
                score = Double.parseDouble(JOptionPane.showInputDialog("Enter the score of " + name + ":"));

                if (score < 0 || score > 100) {
                    throw new IllegalArgumentException("Score must be between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid score. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                continue;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            result.names[currentIndex] = name;
            result.scores[currentIndex] = score;
            currentIndex++;
        }

        return result;
    }

    private static LowestScores findLowestScores(PromptResult result) {
        double lowestScore = Double.MAX_VALUE;
        double secondLowestScore = Double.MAX_VALUE;

        // Iterate through scores to find the lowest and second-lowest scores
        for (int i = 0; i < result.scores.length; i++) {
            // If the current score is lower than the lowest score, of course it becomes the new lowest score
            // Also yeet the previous lowest score to the second-lowest score
            if (result.scores[i] < lowestScore) {
                secondLowestScore = lowestScore;
                lowestScore = result.scores[i];
                // If the current score is between the lowest and second-lowest scores, it becomes the new second-lowest score
            } else if (result.scores[i] < secondLowestScore && result.scores[i] != lowestScore) {
                secondLowestScore = result.scores[i];
            }
        }

        // If no second-lowest score was found, set it to -1
        if (secondLowestScore == Double.MAX_VALUE) {
            secondLowestScore = -1;
        }

        String[] lowestNames = new String[result.names.length];
        String[] secondLowestNames = new String[result.names.length];

        int lowestCount = 0;
        int secondLowestCount = 0;

        // Collect names of students with the lowest and second-lowest scores
        for (int i = 0; i < result.scores.length; i++) {
            if (result.scores[i] == lowestScore) {
                lowestNames[lowestCount++] = result.names[i];
            } else if (result.scores[i] == secondLowestScore) {
                secondLowestNames[secondLowestCount++] = result.names[i];
            }
        }

        // Create a new LowestScores object to hold the results
        LowestScores lowestScores = new LowestScores();

        lowestScores.lowestScore = lowestScore;
        lowestScores.secondLowestScore = secondLowestScore;

        // Resize the arrays to the actual counts
        lowestScores.lowestNames = Arrays.copyOf(lowestNames, lowestCount);
        lowestScores.secondLowestNames = Arrays.copyOf(secondLowestNames, secondLowestCount);

        return lowestScores;
    }

    private void onSubmit() {
        try {
            int numberOfStudents = Integer.parseInt(numberOfStudentsInput.getText());
            if (numberOfStudents < 2) {
                throw new IllegalArgumentException("Number of students must be at least 2.");
            }

            PromptResult studentData = getStudentData(numberOfStudents);
            LowestScores lowestScores = findLowestScores(studentData);

            String concatenatedLowestNames = String.join(", ", lowestScores.lowestNames);
            String concatenatedSecondLowestNames = String.join(", ", lowestScores.secondLowestNames);

            String message = String.format("Lowest Score: %s with %.2f\nSecond Lowest Score: %s with %.2f",
                    concatenatedLowestNames, lowestScores.lowestScore,
                    !concatenatedSecondLowestNames.isEmpty() ? concatenatedSecondLowestNames : "No one", lowestScores.secondLowestScore);

            JOptionPane.showMessageDialog(null, message, "Lowest Scores", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void prepareForm() {
        numberOfStudentsInput = new TextInput();

        FormItem[] formItems = new FormItem[]{
                new FormItem("Number of students:", numberOfStudentsInput),
        };

        Form inputForm = new Form(formItems, e -> onSubmit());

        this.mainWindow.insertForm(inputForm);
    }
}
