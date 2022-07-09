import javax.swing.*;

public class Fruit {
    ImageIcon icon;
    int speed;
    int damage;
    int count;
    int x;
    int y;


    public Fruit () {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ImageIcon getIcon () {
        return this.icon;
    }

    public void setSpeed (int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean hit(Cat cat) {
        // Makes sure that Cat only has to touch icon, instead of specific pixel.

        double hitWidth = getIcon().getIconWidth() * 0.75;
        double hitHeight = getIcon().getIconHeight() * 0.75;

        if (cat.getX() - this.x > -hitWidth && cat.getX() - this.x < hitWidth
                && cat.getY() - this.y > -hitHeight && cat.getY() - this.y < hitHeight) {

            cat.reduceLives(damage);
            cat.setHit(true);
            reset();
            return true;
        }
        return false;
    }
    public void reset () {

    }
}
