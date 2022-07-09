import javax.swing.*;

public class Watermelon extends Fruit{

    String direction;

    public Watermelon (String direction) {
        this.direction = direction;
        this.icon = new ImageIcon("watermelon.png");
        this.damage = 3;
        reset();
    }

    public void move() {
        switch (direction) {
            // Watermelon goes diagonally.
            case "left" -> { //starts on left upper corner
                x += speed;
                y += speed *3;
                break;
            }
            case "right" -> { // starts on right upper corner
                x -= speed;
                y += speed *3;
            }
        }
    }

    public void reset() {
        switch (direction) {
            case "left" :
                setX(getIcon().getIconWidth());
                setY(101 - getIcon().getIconHeight()/2);
                break;

            case "right" :
                setX(450 - getIcon().getIconWidth());
                setY(101 - getIcon().getIconHeight()/2);
        }
    }

    public void keepInBounds () {
        switch (direction) {
            case "left" :
                if (getX() > 450 - getIcon().getIconWidth()) {
                    reset();
                }
                if (getY() > 801 - getIcon().getIconWidth() *2) {
                    reset();
                }
                break;

            case "right" :
                if (getX() <  getIcon().getIconWidth()) {
                    reset();
                }
                if (getY() > 801 - getIcon().getIconWidth() *2) {
                    reset();
                }
        }
    }
}


