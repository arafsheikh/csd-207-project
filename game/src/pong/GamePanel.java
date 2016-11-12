package pong;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GamePanel extends JPanel implements ActionListener, KeyListener 
{
Player1 player1 = new Player1();
Ball ball = new Ball();
Player2 player2 = new Player2();
public GamePanel() 
{
 Timer time = new Timer(50, this);
 time.start();
 this.addKeyListener(this);
 this.setFocusable(true);
}
private void update() 
{
        player1.update();
        ball.update();
        player2.update();
        ball.checkCollisionWith(player1);
        ball.checkCollisionWith(player2);
        ball.hitWall();
}
public void paintComponent(Graphics g) 
{
g.setColor(Color.black);
g.fillRect(0, 0, Game.window_width, Game.window_height);

        player1.paint(g);
        ball.paint(g);
        player2.paint(g);

        g.setColor(Color.blue);
        g.drawLine(0, 30, Game.window_width, 30);
        g.drawLine(Game.window_width / 2, 30, Game.window_width / 2, Game.window_height);
        g.drawOval((Game.window_width / 2) - 30, Game.window_height / 2 - 30, 60, 60);
        g.setColor(Color.yellow);



    }

    public Ball getBall() {
        return ball;
    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    public void keyPressed1(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player1.setYVelocity(-10);
            if (player1.getY() < 30) {
                player1.setYVelocity(0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player1.setYVelocity(10);
            if (player1.getY() + 40 > Game.window_height - 28) {
                player1.setYVelocity(0);
            }
        }
    }

    public void keyReleased1(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
            player1.setYVelocity(0);

        }
    }
   public void keyPressed2(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player2.setYVelocity(-10);
            if (player2.getY() < 30) {
                player2.setYVelocity(0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player2.setYVelocity(10);
            if (player2.getY() + 40 > Game.window_height - 28) {
                player2.setYVelocity(0);
            }
        }
    }

    public void keyReleased2(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_S) {
            player2.setYVelocity(0);

        }
    }
      
}
