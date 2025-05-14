package UI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HyperLinkButton extends JButton {
    private URL url;

    public HyperLinkButton(String text, String url) {
        // Initialize the button, setting text content and basic styling
        super(text);
        setForeground(Color.WHITE);
        setBackground(null);
        setBorder(BorderFactory.createEmptyBorder());
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // add underline to the button
        Font font = getFont().deriveFont(10.0f);
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        setFont(font.deriveFont(attributes));

        // bind the onClick event listener
        try {
            this.url = new URL(url);
            addActionListener(e -> onClick());
        } catch (MalformedURLException e) {
            System.err.println("Malformed URL: " + url);
        }
    }

    // onClick event listener
    // Fortunately lambda expression is already a thing in Java 8 :)
    private void onClick() {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                // Open my portfolio in the browser :D
                desktop.browse(url.toURI());
            } catch (Exception err) {
                System.out.println("Browse failed");
            }
        }
    }
}
