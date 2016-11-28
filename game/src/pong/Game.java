package pong;
import javax.swing.*;
public class Game extends JFrame {
final static int window_width = 683;
final static int window_height = 384;
public Game() 
{    
GetIP.GetIP();    
setSize(window_width, window_height);
setResizable(false);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
add(new GamePanel());
setVisible(true);
}

}
    

