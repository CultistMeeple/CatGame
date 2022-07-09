import javax.swing.*;

public class Cat {
   private int lives;
   private int score;
   private int speed;
   private int x;
   private int y;
   private boolean isHit;
   private ImageIcon icon;
   public int damageTaken;

    public Cat () {
       icon = new ImageIcon("cat.png");
       this.lives = 9;
       this.score = 0;
       this.speed = 10;
       isHit = false;
   }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX (int x) {
       this.x = x;
    }
    public void setY (int y) {
        this.y = y;
    }

    public void moveX(int moveBy) {
       this.x += moveBy;
    }

    public void moveY(int moveBy){
       this.y += moveBy;
    }

    public ImageIcon getIcon() {
       return icon;
   }
    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public boolean isHit() {
        return isHit;
    }

    public void reduceLives (int reduceBy) {
       this.lives = getLives() - reduceBy;
       setDamageTaken(reduceBy);
    }
    public void setDamageTaken (int damage) {
        this.damageTaken += damage;

    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getScore() {
        return score;
    }
    public void increaseScore (int increaseBy) {
       this.score = getScore() + increaseBy;
    }
    public void setScore(int score) {
       this.score = score;
    }

    public void reset() {
        x = 300 - getIcon().getIconWidth()/2;
        y = 300 - getIcon().getIconHeight()/2;
    }

    public boolean hasLives() {

        if (lives <= 0) {
            return false;
        }
        return true;
    }

    public void keepInBounds() {
        if (getX() < getIcon().getIconWidth()/2) {
            setX(450 - getIcon().getIconWidth());
        }

        if (getX() > 450 - getIcon().getIconWidth()) {
            setX(getIcon().getIconWidth());
        }

        if (getY() <= 101) {
            setY(801 - getIcon().getIconHeight() *2);
        }

        if (getY() > 801 - getIcon().getIconHeight() *2) {
            setY(101 + getIcon().getIconHeight());
        }
    }
}
