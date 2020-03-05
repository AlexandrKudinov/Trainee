package gameOfLife;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        add(new GameField(new Display(), new Logic()));
        pack();
        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
