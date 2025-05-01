package UI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SubmitButton extends JButton {
    public SubmitButton(ActionListener listener) {
        super("Submit");
        addActionListener(listener);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        setBorder(BorderFactory.createCompoundBorder(
                getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }
}
