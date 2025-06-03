import UI.MainWindow;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main {
    public static void main(String[] args) {
        // Set the Look and Feel to Flat Dark
        FlatDarkLaf.setup();

        // Create and display the main window
        new MainWindow();
    }
}
