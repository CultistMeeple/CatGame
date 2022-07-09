import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
public class GamePanel extends JPanel implements ActionListener {
    private boolean isStarted = false;
    private boolean isPaused = false;
    public Cat cat;
    public Mouse mouse;
    public static ArrayList <Pineapple> listOfPineapples;
    private int numberOfPineapples;
    private final ArrayList <Watermelon> listOfWatermelons;
    private int numberOfWatermelons;
    static Random random;
    private final Timer timer;
    ImageIcon hit = new ImageIcon("hit64px.png");
    ImageIcon heart = new ImageIcon( "heart.png");
    ImageIcon empty = new ImageIcon("empty.png");
    ImageIcon smallHit = new ImageIcon( "hit24px.png");

    public GamePanel() {
        this.setFocusable(true);
        random = new Random();
        cat = new Cat();
        mouse = new Mouse();
        timer = new Timer(100, this);
        listOfPineapples = new ArrayList<>();
        listOfWatermelons = new ArrayList<>();

        cat.reset(); //Shows Cat before game is started.
        mouse.reset(); //Shows Mouse before game is started.

        Action upAction = new upAction();
        Action downAction = new downAction();
        Action leftAction = new leftAction();
        Action rightAction = new rightAction();
        Action spaceAction = new spaceAction();

        // Duplicated, so that works with Caps Lock

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('w'), "upAction");
        getActionMap().put("upAction", upAction);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(("W")), "upAction");
        getActionMap().put("upAction", upAction);

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('s'), "downAction");
        getActionMap().put("downAction", downAction);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "downAction");
        getActionMap().put("downAction", downAction);

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('a'), "leftAction");
        getActionMap().put("leftAction", leftAction);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "leftAction");
        getActionMap().put("leftAction", leftAction);

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('d'), "rightAction");
        getActionMap().put("rightAction", rightAction);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "rightAction");
        getActionMap().put("rightAction", rightAction);

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "spaceAction");
        getActionMap().put("spaceAction", spaceAction);
    }

    public void setUp() {
        mouse.setSpeed(5);
        cat.setScore(0);
        cat.setLives(9);
        cat.setSpeed(10);

        cat.reset();
        mouse.reset();

        listOfPineapples.clear();
        listOfWatermelons.clear();

        isStarted = true;
        timer.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(50, 0, 500, 50);

        g.setColor(Color.BLUE);
        g.fillRect(0, 101, 450, 700);


        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 15));
        g.drawString("Mouse: +1 score ", 250, 125);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 15));
        g.drawString("Pineapple: -1 life", 250, 145);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 15));
        g.drawString("Watermelon: -3 lives", 250, 165);

        if (isStarted) {
            for (Pineapple p : listOfPineapples) {

                p.getIcon().paintIcon(this, g, p.getX(), p.getY());
            }

            for (Watermelon w : listOfWatermelons) {
                w.getIcon().paintIcon(this, g, w.getX(), w.getY());
            }
        }

        if (!isStarted) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.BOLD, 25));
            g.drawString("WELCOME!", 125, 350);
            g.drawString("Press SPACE to start", 125, 380);
            g.drawString("Use W,A,S,D to move", 125, 410);
        }

        if (isPaused) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.BOLD, 25));
            g.drawString("PAUSED", 125, 350);
            g.drawString("Press SPACE to unpause", 125, 380);
        }

        if (!cat.hasLives()) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.BOLD, 25));
            g.drawString("GAME OVER!", 125, 350);
            g.drawString("Press SPACE to restart", 125, 380);
        }

        //HEADER
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 450, 101);

        String catGame = "Cat Game";
        Graphics2D graphics2D = (Graphics2D) g.create();
        g.setFont(new Font("arial", Font.BOLD, 30));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int center = (450 /2 - fontMetrics.stringWidth(catGame));
        g.setColor(Color.BLACK);
        g.drawString(catGame, center, 30);

        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", Font.BOLD, 20));
        g.drawString("Score: " + cat.getScore(), (450 /2 - fontMetrics.stringWidth("Score")), 60);

        // LIVES

        int livesX = 117; // (450 - 24 * 9 /2)
        int livesY= 75;

        for (int i = 0; i < cat.getLives(); i++) {

            heart.paintIcon(this, g,livesX, livesY);

            livesX = livesX + heart.getIconWidth();
        }

        if (cat.isHit()) {

            int drawDamage = livesY;

            for (int i = 0; i <cat.damageTaken; i++) {
                smallHit.paintIcon(this, g, livesX, drawDamage);
                drawDamage -= smallHit.getIconWidth();
            }

            cat.setDamageTaken(0);
        }

        for (int i = 0; i < 9 - cat.getLives(); i++) {


            empty.paintIcon(this, g,livesX, livesY);

            livesX = livesX + empty.getIconWidth();
        }

        // CAT & MOUSE

        mouse.getIcon().paintIcon(this, g, mouse.getX(), mouse.getY());
        //cat.getIcon().paintIcon(this, g, cat.getX(), cat.getY());

        if (!cat.isHit()) {
            cat.getIcon().paintIcon(this, g, cat.getX(), cat.getY());
        } else {
            hit.paintIcon(this, g, cat.getX(), cat.getY());
            cat.setHit(false);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (cat.getLives() <= 0) {
            timer.stop();
        }
        adjustSpeed(); // must be above getScoreLevel();, else it messes with number of fruit!
        getScoreLevel(); // Important: Refreshes the number of fruit!

        // PINEAPPLES
        while (numberOfPineapples - (listOfPineapples.size()) > 0) {
                listOfPineapples.add(new Pineapple());
        }

        for (Pineapple pineapple : listOfPineapples) {
            pineapple.move();
            pineapple.keepInBounds();
            pineapple.hit(cat);
        }

        // WATERMELONS
        if (numberOfWatermelons == 1 && listOfWatermelons.size() < numberOfWatermelons) {
            listOfWatermelons.add( new Watermelon("left"));
        }
        if (numberOfWatermelons == 2 && listOfWatermelons.size() < numberOfWatermelons) {
            listOfWatermelons.add(new Watermelon("right"));
        }
        for (Watermelon a : listOfWatermelons) {
            a.move();
            a.keepInBounds();
            a.hit(cat);
        }
        cat.keepInBounds();
        mouse.caught(cat);
        mouse.setMovement();
        mouse.keepInBounds();

        repaint();
    }

    public int getScoreLevel() {

        if (cat.getScore() >= 15) {
            numberOfWatermelons = 2;
            numberOfPineapples = 7;
            return 5;
        }
        if (cat.getScore() >= 12) {
            numberOfPineapples = 6;
            return 4;
        }

        if (cat.getScore() >= 9) {
            numberOfWatermelons = 1;
            numberOfPineapples = 5;
            return 3;
        }

        if (cat.getScore() >= 6) {
            return 2;
        }

        if (cat.getScore() >= 3) {
            numberOfPineapples = 4;
            return 1;
        }
        numberOfPineapples = 3;
        numberOfWatermelons = 0;
        return 0;
    }
    public void adjustSpeed() {
        switch (getScoreLevel()) {
            case 5:

            case 4:
                for (Pineapple a : listOfPineapples) {
                    a.setSpeed(20);
                }

            case 3:
                    for (Watermelon a : listOfWatermelons) {
                        a.setSpeed(8);
                    }
                    break;
            case 2:

                for (Pineapple a : listOfPineapples) {
                    a.setSpeed(15);
                }
                break;

            case 1:

            default:
                numberOfPineapples = 2;
                for (Pineapple a : listOfPineapples) {
                    a.setSpeed(12);
                }
        }
    }


    class upAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            cat.moveY(- cat.getSpeed()); // must be negative
        }
    }
    class downAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            cat.moveY(cat.getSpeed()); // must be positive
        }
    }

    class leftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            cat.moveX(- cat.getSpeed()); // must be negative
        }
    }

    class rightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            cat.moveX(cat.getSpeed()); // must be positive
        }
    }

    class spaceAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isStarted || !cat.hasLives()) {
                timer.restart();
                setUp();
                return;
            }
            if (isStarted && !isPaused) {
                repaint();
                timer.stop();
                isPaused = true;
                return;
            }
            if (isPaused) {
                isPaused = false;
                timer.start();
            }
        }
    }
}