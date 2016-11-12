package pong;
import java.awt.*;
import javax.swing.*;
public class Ball 
{
 private int x = Game.window_width / 2;
    private int y = Game.window_height / 2;
    private int Vx = -4;
    private int Vy = 4;
    private int size = 5;
    private int player1Score = 0;
    private int player2Score = 0;

    public void update() {
        x = x + Vx;
        y = y + Vy;

        if (x < 0) {
            Vx = 5;
            player2Score = player2Score + 1;
        } else if (x + size > Game.window_width - 6) {
            Vx = -5;
            player1Score = player1Score + 1;
        }

        if (y < 0) {
            Vy = 5;
        } else if (y + size > Game.window_height - 28) {
            Vy = -5;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(x, y, size, size);
        g.drawString(toPlayer(), 5, 15);
        g.drawString(toComputer(), 280, 15);
    }

    private void reverseDirectionX() {
        Vx = -Vx;
        
    }

    private void reverseDirectionY() {
        Vy = -Vy;
       
    }

    public void checkCollisionWith(Player1 player1) {
        if (this.x > player1.getX() && this.x < player1.getX() + player1.getWidth()) {
            if (this.y > player1.getY() && this.y < player1.getY() + player1.getHeight()) {
                reverseDirectionX();
            }
        }
    }

    public void hitWall() {
        if (this.y < 30) {
            reverseDirectionY();
        }
    }

    public void checkCollisionWith(Player2 player2) {
        if (this.x > player2.getX() && this.x < player2.getX() + player2.getWidth()) {
            if (this.y > player2.getY() && this.y < player2.getY() + player2.getHeight()) {
                reverseDirectionX();
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public String toPlayer() {
        String retVal = "";
        retVal = "Player1 Score: " + player1Score;
        return retVal;
    }

    public String toComputer() {
        String retVal = "";
        retVal = "Player2 Score: " + player2Score;
        return retVal;
    }
}
    

