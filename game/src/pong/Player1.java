package pong;
import java.awt.*;
public class Player1 {
    private int y = Game.window_height / 2;
    private int Vy = 0;
    private int width = 10;
    private int height = 60;

    public Player1() {
    }

    public void update() {
        y = y + Vy;
    }

    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(35, y, width, height);
    }

    public void setYVel(int speed) {
        Vy = speed;
    }

    public int getX() {
        return 35;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
    

