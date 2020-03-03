package gameOfLife;
import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        add(new GameField());
        pack();
        setTitle("Life");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
         new MainWindow();
    }
}
