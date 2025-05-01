package Tasks;

import UI.MainWindow;

public abstract class TaskInstance {
    public String name = "";
    protected String description = "";

    protected final MainWindow mainWindow;

    protected TaskInstance(MainWindow window) {
        mainWindow = window;
    }

    public void run() {
        mainWindow.setTaskDescription(description);
        prepareForm();
    }

    protected abstract void prepareForm();
}
