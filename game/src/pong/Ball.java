package pong;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Ball extends JFrame
{
 private int x = Game.window_width / 2;
 private int y = Game.window_height / 2;
    private int Vx = -10;
    private int Vy = 10;
    private int size = 10;
    private int player1Score = 0;
    private int player2Score = 0;
    int i = 0;
    public void update() {
        x = x + Vx;
        y = y + Vy;

        if (x < 0) {
            Vx = 10;
            player2Score = player2Score + 1;
            ScoreCheck();
        } else if (x + size > Game.window_width) {
            Vx = -10;
            player1Score = player1Score + 1;
            ScoreCheck();
        }

        if (y < 0) {
            Vy = 10;
        } else if (y + size > Game.window_height) {
            Vy = -10;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, size, size);
       g.drawString(toPlayer1(),5, 15);
       g.drawString(toPlayer2(),580, 15);
    }
    private void revDirX() {
        Vx = -Vx;
        
    }

    private void revDirY() {
        Vy = -Vy;
       
    }

    public void chkCollision(Player1 player1) {
        if (this.x > player1.getX() && this.x < player1.getX() + player1.getWidth()) {
            if (this.y > player1.getY() && this.y < player1.getY() + player1.getHeight()) {
                revDirX();
            }
        }
    }

    public void hitWall() {
        if (this.y < 30) {
            revDirY();
        }
    }

    public void chkCollision(Player2 player2) {
        if (this.x > player2.getX() && this.x < player2.getX() + player2.getWidth()) {
            if (this.y > player2.getY() && this.y < player2.getY() + player2.getHeight()) {
                revDirX();
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

    public String toPlayer1() {
        String retVal = "";
        retVal = "Player1 Score: " + player1Score;
        return retVal;
    }

    public String toPlayer2() {
        String retVal = "";
        retVal = "Player2 Score: " + player2Score;
        
        return retVal;
    }
   public void ScoreCheck()
   {
    if(player1Score == 30){
        JOptionPane.showMessageDialog(null,"PLAYER 1 WINS","", JOptionPane.INFORMATION_MESSAGE);
        i++;
        }
    if(player2Score == 30){
       JOptionPane.showMessageDialog(null,"PLAYER 2 WINS","", JOptionPane.INFORMATION_MESSAGE);
        i++;
        }
    if(i == 1)
    {
      PullThePlug();  
    }
   }
   public void PullThePlug()
   {
    WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    setVisible(false);
    dispose();
    System.exit(0); 
   }  
}
 
    

