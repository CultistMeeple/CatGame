import javax.swing.*;
import java.awt.*;

public class Mouse {

    private int speed;
    private int x;
    private int y;

    private String direction;
    private ImageIcon icon;
    private ImageIcon iconRight;
    private ImageIcon iconLeft;

    public Mouse () {
        icon = new ImageIcon((""));
        iconRight = new ImageIcon("mouseRight.png");
        iconLeft = new ImageIcon("mouseLeft.png");
        this.direction = getDirection ();
    }

    private String getDirection() {
        String [] dir = {"right", "left"};
        return dir[(int) (Math.random() * dir.length)];
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setMovement () {

        switch (this.direction) {
            case "left":
                icon = iconLeft;
                this.x -= speed;
                break;
            case "right" :
                icon = iconRight;
                this.x +=speed;
        }
    }

    public void reset () {
        setX(GamePanel.random.nextInt(50 + getIcon().getIconWidth() *2,450 - getIcon().getIconWidth() *2));

        setY(GamePanel.random.nextInt(151 +getIcon().getIconHeight() *2, 801 - getIcon().getIconHeight() *2));
        this.direction = getDirection ();
    }

    public ImageIcon getIcon () {
        return icon;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean caught(Cat cat) {

        double hitWidth = getIcon().getIconWidth() *0.75;
        double hitHeight = getIcon().getIconHeight() *0.75;

        // Makes sure that Cat only has to touch Mouse's icon, instead of specific pixel.

        if (cat.getX() - getX() > -hitWidth && cat.getX() - getX() < hitWidth
                && cat.getY() - getY() > -hitHeight && cat.getY() - getY() < hitHeight) {
            cat.increaseScore(1);
            reset();
            return true;
        }
        return false;
    }

    public void keepInBounds() {
        if (getX() < getIcon().getIconWidth()/2) {
            setX(450 - getIcon().getIconWidth()*2);
        }
        if (getX() > 450 - getIcon().getIconWidth()) {
            setX(getIcon().getIconWidth());
        }
    }
}
