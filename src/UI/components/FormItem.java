package UI.components;

import java.awt.*;

public class FormItem {
    protected String name;
    protected Component input;

    public FormItem(String name, Component component) {
        this.name = name;
        this.input = component;
    }
}
