package UI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Form extends JPanel {
    FormItem[] items;
    GridBagConstraints gbc;
    ActionListener actionListener;

    public Form(FormItem[] items, ActionListener actionListener) {
        super();
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        this.items = items;
        this.actionListener = actionListener;

        prepareForm();
    }

    public Form(FormItem[] items) {
        super();
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        this.items = items;

        prepareForm();
    }

    private void prepareForm() {
        // Gap between each component
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.gridy = 0;

        // Append each label and text input into the form
        for (FormItem item : items) {
            gbc.gridx = 0;
            add(new JLabel(item.name), gbc);

            gbc.gridx = 1;
            gbc.weightx = 1.0;
            add(item.input, gbc);

            gbc.gridy++;
        }

        // Glue to push Submit to bottom
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(Box.createVerticalGlue(), gbc);

        if (actionListener == null) {
            return;
        }

        // Submit button
        SubmitButton submitButton = new SubmitButton(actionListener);
        gbc.gridy++;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(submitButton, gbc);
    }
}
