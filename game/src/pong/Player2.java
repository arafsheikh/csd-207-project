package pong;
import java.awt.*;
public class Player2 {
private int y = Game.window_height / 2;
private int Vy = 0;
private int width = 10;
private int height = 60;

public Player2() {
    }

public void update() {
        y = y + Vy;
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(Game.window_width - (35 + width), y, width, height);
    }

    public int getX() {
        return Game.window_width - 6 - (35 + width);
    }

    public void setYVel(int speed) {
        Vy = speed;
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

    

