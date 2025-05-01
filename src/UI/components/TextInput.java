package UI.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextInput extends JTextField {
    public TextInput() {
        super();

        initBorder();
        addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                focusBorder();
            }

            public void focusLost(FocusEvent e) {
                initBorder();
            }
        });
    }

    private void initBorder() {
        Border paddingBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border underlineBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY);
        Border inputBorder = BorderFactory.createCompoundBorder(underlineBorder, paddingBorder);
        setBorder(inputBorder);
    }

    private void focusBorder() {
        Border paddingBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY);
        Border inputBorder = BorderFactory.createCompoundBorder(border, paddingBorder);
        setBorder(inputBorder);
    }
}
