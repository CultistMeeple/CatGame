import javax.swing.*;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;

public class Pineapple extends Fruit{

    ArrayList <Integer> listOfX;

    public  Pineapple () {
        this.icon = new ImageIcon("pineapple64px.png");
        this.damage = 1;
        listOfX = new ArrayList<>();
        reset();
    }

    public void keepInBounds() {
        if (getY() > 801 - getIcon().getIconHeight() *2) {
            reset();
        }

        }
    public void move() {
        // Makes the Pineapple fall vertically.
        this.y += speed;
    }


    public void reset() {

        x = GamePanel.random.nextInt(50, 400) ;
        y = 101 - getIcon().getIconHeight() *2;
    }

        }







