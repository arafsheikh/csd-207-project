package pong;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class OpeningScreen {
public OpeningScreen() {
JFrame OpenScr = new JFrame();
OpenScr.setLayout(new BorderLayout());
OpenScr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
textLabel = new JLabel(theText, SwingConstants.CENTER);
OpenScr.add(textLabel, BorderLayout.CENTER);
  
        Font oldFont = textLabel.getFont();
        textLabel.setFont(oldFont.deriveFont(2 * oldFont.getSize2D()));
        OpenScr.getContentPane().setBackground( Color.BLACK);    
        JButton StartButton = new JButton("START GAME");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(StartButton);
        OpenScr.add(buttonPanel, BorderLayout.SOUTH);
        StartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Game();
            }
        });

        Timer makeItBlink = new Timer(blinkInterval,
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        blinkState = !blinkState;
                        if (blinkState) {
                            textLabel.setForeground(colors[colorIndex]);
                            colorIndex = (colorIndex + 1) % colors.length;
                            if(colorIndex == 3)
                            colorIndex = 0;    
                        }
                        
                    }
                });
        
        makeItBlink.start();

        OpenScr.setSize(initialDimensions);

       OpenScr.setVisible(true);
    }

   

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OpeningScreen();
            }
            });
    
    }
    private static Color[] colors = new Color[] {
        Color.red, Color.green, Color.blue, Color.yellow 
    };
    
    private static String theText = "WELCOME TO PONG";
    private static Dimension initialDimensions = new Dimension(683, 384);
    private static int blinkInterval = 200;
    private JLabel textLabel;
    private int colorIndex = 0;
    private boolean blinkState = false;
}
    

