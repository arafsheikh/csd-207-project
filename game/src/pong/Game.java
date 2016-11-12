package pong;
import javax.swing.*;
public class Game extends JFrame {
final static int window_width = 700;
final static int window_height = 450;
public Game() 
{
setSize(window_width, window_height);
setResizable(false);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
add(new GamePanel());
setVisible(true);
}
public static void main(String[] args) {
new Game();
}
}
    

