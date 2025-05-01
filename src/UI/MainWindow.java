package UI;

import Tasks.TaskInstance;
import Tasks.items.*;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.Border;

import UI.components.HyperLinkButton;
import com.formdev.flatlaf.FlatLightLaf;

public class MainWindow {
    private TaskInstance[] taskInstances;
    private JPanel headerPanel;
    private JComboBox<String> taskSelectBox;
    private JPanel taskContainer;
    private JPanel footerLabelPanel;

    private JLabel taskDescriptionLabel;
    private JPanel taskPanel;

    public MainWindow() {
        prepareTaskInstances();
        prepareGUI();
        taskInstances[0].run();
    }

    public void setTaskDescription(String description) {
        taskDescriptionLabel.setText("<html>" + description + "</html>");
    }

    public void insertForm(JPanel formPanel) {
        taskPanel.add(formPanel);
    }

    private void onTaskChange(String task) {
        // Reset task description and implementation panel
        taskDescriptionLabel.setText("");
        taskPanel.removeAll();

        // Look for the targeted task and run
        for (TaskInstance taskInstance : taskInstances) {
            if (taskInstance.name.equals(task)) {
                taskInstance.run();
            }
        }
    }

    private void prepareTaskInstances() {
        taskInstances = new TaskInstance[]{
                new P2Task1(this),
                new P2Task2Linear(this),
                new P2Task3(this),
                new P2Task4(this),
                new P2Task5(this)
        };
    }

    private void prepareHeader() {
        headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        // Define the title content
        JLabel mainTitle = new JLabel("[ITS63304] Practical 2", JLabel.CENTER);
        JLabel secondaryTitle = new JLabel("Task Implementations", JLabel.CENTER);

        // Adjust the font weight and size
        mainTitle.setFont(mainTitle.getFont().deriveFont(Font.BOLD).deriveFont(18.0f));
        secondaryTitle.setFont(secondaryTitle.getFont().deriveFont(14.0f));

        // Center the labels
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondaryTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the labels into the panel with a little gap in between
        headerPanel.add(mainTitle);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        headerPanel.add(secondaryTitle);
    }

    private void prepareTaskSelectBox() {
        // Extract task name from each task instance in the instances array
        String[] taskNames = new String[taskInstances.length];
        for (int i = 0; i < taskInstances.length; i++) {
            taskNames[i] = taskInstances[i].name;
        }

        // Initialize the task selection box
        taskSelectBox = new JComboBox<>(taskNames);
        taskSelectBox.setSelectedIndex(0);
        taskSelectBox.setBorder(BorderFactory.createCompoundBorder(
                taskSelectBox.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        taskSelectBox.addActionListener(e -> onTaskChange(Objects.requireNonNull(taskSelectBox.getSelectedItem()).toString()));
    }

    private void prepareTaskPanel() {
        taskContainer = new JPanel();
        taskContainer.setLayout(new BoxLayout(taskContainer, BoxLayout.Y_AXIS));
        taskContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        taskContainer.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
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

        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        taskPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

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
        mainFrame.setSize(600, 570);
        mainFrame.setMinimumSize(new Dimension(600, 570));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the main element container with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Border padding = BorderFactory.createEmptyBorder(16, 16, 16, 16);
        mainPanel.setBorder(padding);

        // Initialize each element / element group
        prepareHeader();
        prepareTaskSelectBox();
        prepareTaskPanel();
        prepareFooter();

        // Add all elements into the main container
        mainPanel.add(headerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 16)));
        mainPanel.add(taskSelectBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(taskContainer);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(footerLabelPanel);

        mainFrame.setContentPane(mainPanel);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();

        new MainWindow();
    }
}
