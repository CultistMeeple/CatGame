import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        GamePanel gamePanel = new GamePanel();

        JFrame frame = new JFrame("Cat Game");
        frame.setSize(450, 801);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setVisible(true);

    }
}
