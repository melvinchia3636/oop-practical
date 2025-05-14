package UI.components;

import javax.swing.*;
import java.awt.*;

public class ComboBoxRenderer extends JPanel implements ListCellRenderer<Object>
{

    private static final long serialVersionUID = -1L;
    private Color[] colors;
    private String[] strings;

    JPanel textPanel;
    JLabel text;

    public ComboBoxRenderer(JComboBox<String> combo) {

        textPanel = new JPanel();
        textPanel.add(this);
        text = new JLabel();
        text.setOpaque(true);
        text.setFont(combo.getFont());
        textPanel.add(text);
    }

    public void setColors(Color[] col)
    {
        colors = col;
    }

    public void setStrings(String[] str)
    {
        strings = str;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected)
        {
            setBackground(list.getSelectionBackground());
        } else {
            setBackground(list.getBackground());
        }

        if (colors.length != strings.length)
        {
            System.out.println("colors.length does not equal strings.length");
            return this;
        }

        text.setBackground(getBackground());

        text.setText(value.toString());
        if (index>-1) {
            text.setForeground(colors[index]);
        }
        return text;
    }
}