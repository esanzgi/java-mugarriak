import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboListener implements ActionListener {
    private MainWindow mainWindow;

    public ComboListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.mainWindow.load_image();
    }
}
