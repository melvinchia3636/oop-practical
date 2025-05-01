package UI.components;

import javax.swing.*;
import javax.swing.border.Border;

public class TextInput extends JTextField {
    public TextInput() {
        super();

        initBorder();
    }

    private void initBorder() {
        Border paddingBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        Border inputBorder = BorderFactory.createCompoundBorder(getBorder(), paddingBorder);
        setBorder(inputBorder);
    }
}
