package UI;

import Tasks.TaskGroup;
import Tasks.TaskInstance;
import Tasks.Week2.*;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.Border;

import Tasks.Week3.P3Task1;
import Tasks.Week3.P3Task2;
import Tasks.Week3.P3Task3;
import Tasks.Week4.*;
import UI.components.HyperLinkButton;
import com.formdev.flatlaf.FlatDarkLaf;

public class MainWindow {
    private TaskGroup[] allTasks;

    private JPanel headerPanel;
    private JPanel footerLabelPanel;

    // Components for selecting tasks
    private JComboBox<String> taskGroupComboBox;
    private JComboBox<String> taskSelectBox;

    // Components for displaying task instances
    private JPanel taskContainer;
    private JLabel taskDescriptionLabel;
    private JPanel taskPanel;

    public MainWindow() {
        prepareTaskInstances();
        prepareGUI();

        // Run the first task in the first task group
        onTaskGroupChange(allTasks[0].name);
    }

    // Method exposed to TaskInstance to wrap task descriptions in html tag for text formatting
    public void setTaskDescription(String description) {
        taskDescriptionLabel.setText("<html>" + description + "</html>");
    }

    // Method exposed to TaskInstance to add form into the taskPanel
    public void insertForm(JPanel formPanel) {
        taskPanel.add(formPanel);
    }

    // Handle task group selection, updating task select box accordingly
    private void onTaskGroupChange(String selectedGroup) {
        TaskInstance[] target = new TaskInstance[0];

        // Find the target taskGroup
        for (TaskGroup taskGroup : allTasks) {
            if (taskGroup.name.equals(selectedGroup)) {
                target = taskGroup.taskInstances;
                break;
            }
        }

        // Create an array of task names
        String[] taskNames = new String[target.length];
        for (int i = 0; i < target.length; i++) {
            taskNames[i] = target[i].name;
        }

        // Refresh the task select box with new list of tasks
        taskSelectBox.removeAllItems();
        for (String taskName : taskNames) {
            taskSelectBox.addItem(taskName);
        }
    }

    // Handle task selection
    private void onTaskChange() {
        try {
            String task = Objects.requireNonNull(taskSelectBox.getSelectedItem()).toString();

            // Reset task description and implementation panel
            taskDescriptionLabel.setText("");
            taskPanel.removeAll();

            // Look for the targeted task and run
            for (TaskGroup taskGroup : allTasks) {
                for (TaskInstance taskInstance : taskGroup.taskInstances) {
                    if (taskInstance.name.equals(task)) {
                        taskInstance.run();
                        break;
                    }
                }
            }
        } catch (Exception ignored) {}
    }

    // Manage your task instances here :)
    private void prepareTaskInstances() {
        TaskInstance[] week2Tasks = new TaskInstance[]{
                new P2Task1(this),
                new P2Task2Linear(this),
                new P2Task3(this),
                new P2Task4(this),
                new P2Task5(this),
        };

        TaskInstance[] week3Tasks = new TaskInstance[]{
                new P3Task1(this),
                new P3Task2(this),
                new P3Task3(this),
        };

        TaskInstance[] week4Tasks = new TaskInstance[]{
                new P4Task1(this),
                new P4Task2(this),
                new P4Task3(this),
                new P4Task4(this),
                new P4Task5(this),
                new P4Task5Amended(this),
                new P4Task6(this),
                new P4Task7(this),
        };

        allTasks = new TaskGroup[]{
                new TaskGroup("Week 2 - Linear Implementations", week2Tasks),
                new TaskGroup("Week 3 - Selection Structures", week3Tasks),
                new TaskGroup("Week 4 - Java Class API", week4Tasks),
        };
    }

    private void prepareHeader() {
        headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        // Define the title content
        JLabel mainTitle = new JLabel("[ITS63304] Object Oriented Programming", JLabel.CENTER);
        JLabel secondaryTitle = new JLabel("Practical Task Implementations", JLabel.CENTER);

        // Adjust the font weight and size
        mainTitle.setFont(mainTitle.getFont().deriveFont(Font.BOLD).deriveFont(18.0f));
        secondaryTitle.setFont(secondaryTitle.getFont().deriveFont(16.0f));

        // Center the labels
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondaryTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the labels into the panel with a little gap in between
        headerPanel.add(mainTitle);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        headerPanel.add(secondaryTitle);
    }

    private void prepareTaskGroupsSelectBox() {
        String[] taskGroupNames = new String[allTasks.length];
        for (int i = 0; i < allTasks.length; i++) {
            taskGroupNames[i] = allTasks[i].name;
        }

        taskGroupComboBox = new JComboBox<>(taskGroupNames);
        taskGroupComboBox.setSelectedIndex(0);
        taskGroupComboBox.setBorder(BorderFactory.createCompoundBorder(
                taskGroupComboBox.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        taskGroupComboBox.addActionListener(e -> onTaskGroupChange(Objects.requireNonNull(taskGroupComboBox.getSelectedItem()).toString()));
    }

    private void prepareTaskSelectBox() {
        taskSelectBox = new JComboBox<>();
        taskSelectBox.setBorder(BorderFactory.createCompoundBorder(
                taskSelectBox.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        taskSelectBox.addActionListener(e -> onTaskChange());
    }

    private void prepareTaskPanel() {
        taskContainer = new JPanel();
        taskContainer.setLayout(new BoxLayout(taskContainer, BoxLayout.Y_AXIS));
        taskContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        taskContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        Border border = BorderFactory.createLineBorder(Color.GRAY);
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        taskContainer.setBorder(BorderFactory.createCompoundBorder(border, padding));

        // Initialize the task description and implementation title label
        JLabel taskDescriptionTitleLabel = new JLabel("Task Description:", JLabel.LEFT);
        taskDescriptionTitleLabel.setFont(taskDescriptionTitleLabel.getFont().deriveFont(14.0f).deriveFont(Font.BOLD));
        taskDescriptionTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel taskImplementationTitleLabel = new JLabel("Task Implementation:", JLabel.LEFT);
        taskImplementationTitleLabel.setFont(taskImplementationTitleLabel.getFont().deriveFont(14.0f).deriveFont(Font.BOLD));
        taskImplementationTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Initialize the task description and task panel that will have their content change dynamically
        taskDescriptionLabel = new JLabel();
        taskDescriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // The component containing the task form
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        taskPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // Add everything into the outermost wrapper
        taskContainer.add(taskDescriptionTitleLabel);
        taskContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        taskContainer.add(taskDescriptionLabel);
        taskContainer.add(Box.createRigidArea(new Dimension(0, 16)));
        taskContainer.add(taskImplementationTitleLabel);
        taskContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        taskContainer.add(taskPanel);
    }

    private void prepareFooter() {
        footerLabelPanel = new JPanel();
        footerLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerLabelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // initialize the static text labels in front end behind the link button
        JLabel footerLabel1 = new JLabel("First ever Java GUI app created by", JLabel.CENTER);
        footerLabel1.setFont(footerLabel1.getFont().deriveFont(10.0f));
        footerLabel1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        footerLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel footerLabel2 = new JLabel("with ❤", JLabel.CENTER);
        footerLabel2.setFont(footerLabel2.getFont().deriveFont(10.0f));
        footerLabel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        footerLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // create link button to my portfolio :D
        HyperLinkButton hyperlinkButton = new HyperLinkButton("Melvin Chia", "https://melvinchia.dev");

        // Add everything into the container
        footerLabelPanel.add(footerLabel1);
        footerLabelPanel.add(hyperlinkButton);
        footerLabelPanel.add(footerLabel2);
    }

    private void prepareGUI() {
        // Initialize the main frame
        JFrame mainFrame = new JFrame("[ITS63304] Practical 2");
        mainFrame.setSize(600, 650);
        mainFrame.setMinimumSize(new Dimension(600, 650));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the main element container with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Border padding = BorderFactory.createEmptyBorder(16, 16, 16, 16);
        mainPanel.setBorder(padding);

        // Initialize each element / element group
        prepareHeader();
        prepareTaskGroupsSelectBox();
        prepareTaskSelectBox();
        prepareTaskPanel();
        prepareFooter();

        // Add all elements into the main container
        mainPanel.add(headerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(taskGroupComboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(taskSelectBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(taskContainer);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(footerLabelPanel);

        mainFrame.setContentPane(mainPanel);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();

        new MainWindow();
    }
}
